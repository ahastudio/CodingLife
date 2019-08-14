from scores import class_total


def test_class_total():
    assert class_total([{'국어': 100}], '국어') == 100
    assert class_total([{'국어': 100}, {'국어': 80}], '국어') == 180
    assert class_total([
        {'국어': 100, '영어': 90},
        {'국어': 80, '영어': 20}
    ], '국어') == 180
    assert class_total([
        {'국어': 100, '영어': 90, '수학': 95},
        {'국어': 80, '영어': 20, '수학': 100},
        {'국어': 10, '영어': 20, '수학': 30},
    ], '국어') == 190
