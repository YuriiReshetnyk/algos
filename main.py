from collections import defaultdict
from typing import List


def find_ways_count(corridor: List, columns: int, rows: int):
    memorized_path = defaultdict(int)
    buttons = [[1] for elem in range(rows)]

    for row in range(rows):
        memorized_path[corridor[row][0]] += 1

    for column in range(1, columns):
        ways = {}

        for row in range(rows):
            button = corridor[row][column]

            if button is not corridor[row][column - 1]:
                current_way = buttons[row][column - 1] + memorized_path[button]
            else:
                current_way = memorized_path[button]

            buttons[row].append(current_way)
            ways[button] = current_way + ways.get(button, 0)

        if column < columns:
            for button in ways:
                memorized_path[button] += ways[button]

    if columns == 1:
        return buttons[0][columns - 1]

    return buttons[0][columns - 1] + buttons[rows - 1][columns - 1]


if __name__ == '__main__':
    input_file = open("input.txt", "r")
    row, column = map(int, input_file.readline().split())
    corridor = [[] for _ in range(row)]
    for button in range(column):
        corridor[button] = input_file.readline()
    input_file.close()

    output_file = open("output.txt", "w")
    output_file.write(str(find_ways_count(corridor, row, column)))