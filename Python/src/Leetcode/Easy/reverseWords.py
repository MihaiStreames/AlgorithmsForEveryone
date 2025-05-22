def reverseWords(s: str) -> str:
    final = ""

    for word in s.split():
        final += word[::-1] + " "
    return final[:-1]