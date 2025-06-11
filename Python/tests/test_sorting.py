from typing import Any

import pytest
import requests

from Python.afe.algorithms.sorting import quick_sort

API_URL = "http://127.0.0.1:5001/data"


def get_test_data(params: dict) -> Any | None:
    """Fetches a list of integers from the testing API."""
    try:
        response = requests.get(API_URL, params=params)
        response.raise_for_status()  # Raises an exception for bad status codes
        return response.json()
    except requests.exceptions.RequestException as e:
        # Fail the test if the API is not running or returns an error
        pytest.fail(f"Failed to connect to TestingAPI: {e}")
        return None


def test_quicksort():
    print("Fetching random test data from API for QuickSort...")
    api_data = get_test_data({'type': 'random', 'size': 100})

    expected_sorted_data = sorted(api_data)

    quick_sort(api_data)

    assert api_data == expected_sorted_data
    print("QuickSort test passed!")
