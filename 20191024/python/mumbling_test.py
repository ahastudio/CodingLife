def accum(s):
    return '-'.join(x.upper() + x.lower() * i for i, x in enumerate(s))

def test_sample():
    assert accum('abcd') == 'A-Bb-Ccc-Dddd'
    assert accum('RqaEzty') == 'R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy'
    assert accum('cwAt') == 'C-Ww-Aaa-Tttt'
    assert accum('ZpglnRxqenU') == 'Z-Pp-Ggg-Llll-Nnnnn-Rrrrrr-Xxxxxxx' \
        '-Qqqqqqqq-Eeeeeeeee-Nnnnnnnnnn-Uuuuuuuuuuu'
    assert accum('NyffsGeyylB') == 'N-Yy-Fff-Ffff-Sssss-Gggggg-Eeeeeee' \
        '-Yyyyyyyy-Yyyyyyyyy-Llllllllll-Bbbbbbbbbbb'
    assert accum('MjtkuBovqrU') == 'M-Jj-Ttt-Kkkk-Uuuuu-Bbbbbb-Ooooooo' \
        '-Vvvvvvvv-Qqqqqqqqq-Rrrrrrrrrr-Uuuuuuuuuuu'
    assert accum('EvidjUnokmM') == 'E-Vv-Iii-Dddd-Jjjjj-Uuuuuu-Nnnnnnn' \
        '-Oooooooo-Kkkkkkkkk-Mmmmmmmmmm-Mmmmmmmmmmm'
    assert accum('HbideVbxncC') == 'H-Bb-Iii-Dddd-Eeeee-Vvvvvv-Bbbbbbb' \
        '-Xxxxxxxx-Nnnnnnnnn-Cccccccccc-Ccccccccccc'
