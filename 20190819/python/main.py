import pandas as pd

from scores import update_class_grades


def main():
    class_scores = [
        {'Name': 'Jane', 'Korean': 100, 'English': 100, 'Math': 100},
        {'Name': 'Brown', 'Korean': 10, 'English': 20, 'Math': 30},
        {'Name': 'Susan', 'Korean': 90, 'English': 70, 'Math': 50}
    ]

    subjects = ['Korean', 'English', 'Math']

    for subject in subjects:
        update_class_grades(class_scores, subject)

    columns = []
    for subject in subjects:
        columns += [subject + ' Grade', subject]

    table = pd.DataFrame(class_scores, columns=columns)
    print(table)


main()
