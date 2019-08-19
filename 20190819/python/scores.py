def grade(score):
    grades = {'A': 80, 'B': 60, 'C': 40, 'D': 20}
    for grade, min_score in grades.items():
        if score >= min_score:
            return grade
    return 'F'


def class_grades(class_scores, subject):
    grades = []
    for scores in class_scores:
        grades.append(grade(scores[subject]))
    return grades


def update_class_grades(class_scores, subject):
    grades = class_grades(class_scores, subject)
    for i in range(len(grades)):
        class_scores[i][subject + ' Grade'] = grades[i]
