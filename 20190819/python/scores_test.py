from scores import grade, class_grades, update_class_grades


def test_grade():
    assert grade(10) == 'F'
    assert grade(30) == 'D'
    assert grade(50) == 'C'
    assert grade(70) == 'B'
    assert grade(90) == 'A'
    assert grade(100) == 'A'
    assert grade(0) == 'F'


def test_class_grades():
    assert class_grades([], 'Korean') == []
    assert class_grades([{'Korean': 100}], 'Korean') == ['A']
    assert class_grades([{'Korean': 90}], 'Korean') == ['A']
    assert class_grades([{'Korean': 70}], 'Korean') == ['B']
    assert class_grades([
        {'Korean': 100}, {'Korean': 10}
    ], 'Korean') == ['A', 'F']


def test_update_class_grades():
    class_scores = [{'Korean': 100}, {'Korean': 10}]

    update_class_grades(class_scores, 'Korean')

    assert class_scores[0]['Korean Grade'] == 'A'
    assert class_scores[1]['Korean Grade'] == 'F'
