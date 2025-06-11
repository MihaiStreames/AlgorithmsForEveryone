import random

from flask import Flask, jsonify, request

app = Flask(__name__)


@app.route('/data', methods=['GET'])
def generate_data():
    """
    Generates sample data based on query parameters.
    Query Parameters:
        size (int): The number of elements in the list. Default: 100.
        type (str): The type of data to generate.
                    Options: 'random', 'sorted', 'reversed', 'nearly_sorted', 'with_duplicates'.
                    Default: 'random'.
        min_val (int): The minimum value for the numbers. Default: 0.
        max_val (int): The maximum value for the numbers. Default: 1000.
        swaps (int): For 'nearly_sorted', the number of swaps to perform. Default: 5.
        duplication (float): For 'with_duplicates', the rate of duplication (0.0 to 1.0).
                             Default: 0.3 (meaning ~30% of items will be duplicates).
    Returns:
        A JSON response containing a list of integers.
    """
    try:
        size = int(request.args.get('size', 100))
        data_type = request.args.get('type', 'random').lower()
        min_val = int(request.args.get('min_val', 0))
        max_val = int(request.args.get('max_val', 1000))
        swaps = int(request.args.get('swaps', 5))
        duplication = float(request.args.get('duplication', 0.3))

        if size <= 0 or min_val >= max_val or not (0.0 <= duplication <= 1.0):
            return jsonify({"error": "Invalid query parameters"}), 400

    except (ValueError, TypeError):
        return jsonify({
            "error": "Invalid parameter types. Ensure size, min_val, max_val, and swaps are integers and duplication is a float."
        }), 400

    if data_type == 'random':
        data = [random.randint(min_val, max_val) for _ in range(size)]

    elif data_type == 'sorted':
        data = sorted([random.randint(min_val, max_val) for _ in range(size)])

    elif data_type == 'reversed':
        data = sorted([random.randint(min_val, max_val) for _ in range(size)], reverse=True)

    elif data_type == 'nearly_sorted':
        data = sorted([random.randint(min_val, max_val) for _ in range(size)])
        for _ in range(swaps):
            if size > 1:
                i, j = random.sample(range(size), 2)
                data[i], data[j] = data[j], data[i]

    elif data_type == 'with_duplicates':
        # Create a smaller pool of unique numbers to sample from, ensuring duplicates
        pool_size = max(1, int(size * (1 - duplication)))
        number_pool = [random.randint(min_val, max_val) for _ in range(pool_size)]
        data = [random.choice(number_pool) for _ in range(size)]
        random.shuffle(data)

    else:
        return jsonify({"error": f"Unknown data type: '{data_type}'"}), 400

    return jsonify(data)


if __name__ == '__main__':
    app.run(debug=True, port=5001)
