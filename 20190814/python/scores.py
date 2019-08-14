def class_total(class_scores, subject):
    total_score = 0
    for score in class_scores:
        total_score += score[subject]
    return total_score
