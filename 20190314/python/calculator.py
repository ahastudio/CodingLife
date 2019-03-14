# tkinter library를 tk로 호출하겠다라는 의미.
# tkinter documentation으로 검색해서 코드 확인해볼것
import tkinter as tk

def main():
    def click (text):
        # text 값이 =인 경우
        if text == "=":
            try :
                # 결과값을 계산
                result = str(round(eval(display.get()), 2))
                # 메인 디스플레이를 지우고
                display.delete(0, tk.END)
                # 결과 값을 추가합니다.
                display.insert(tk.END, result)
            except:
                display.insert(tk.END, "-> 오류")
        elif text == "C": # elif를 사용하지 않고 if를 사용하면 =가 하나 더 붙는다.
            # text 값이 "C"이면, 메인 디스플레이를 지웁니다.
            display.delete(0, tk.END)
        elif text == "AC":  # elif = else if
            display.delete(0, tk.END)
        else:
            # 그 외의 버튼을 누르면 그 버튼의 Text 값을 Entry에 출력
            # display에 삽입해라 제일마지막에, 추가하는 값은 key이다.
            display.insert(tk.END, text)

    # 윈도우 생성
    window = tk.Tk() # root와 window의 차이는??
    window.title("Calculator Ver. 1.0")

    # 디스플레이 생성
    # Entry : 한 줄 짜리 입력창 원래는 아이디 입력할때 사용하는 입력창??
    display = tk.Entry (window, width = 35, bg="light green")
    # grid : 주어진 프레임안에서 몇번째 칸에 만들 것인가?
    display.grid(row = 0, column = 0, columnspan = 2)

    # 숫자 버튼을 넣을 프레임
    num_frame = tk.Frame(window)
    num_frame.grid(row = 1, column = 0)

    # 숫자 버튼을 넣어봅시다.
    # tk.Button(num_frame, text="1", width=5).grid(row=0, column=1)

    # 숫자 버튼을 넣어봅시다.
    num_list = ['7', '8', '9', '4', '5', '6', '1', '2', '3', '0', '.', '=']
    r = 0
    c = 0
    for btn_text in num_list:
        def cmd(x = btn_text):
            click(x)
        tk.Button(num_frame, text=btn_text, width = 5, command = cmd).grid(row = r, column = c)
        c = c + 1
        if c > 2:
            c = 0
            r = r + 1

    # 연산자(operator 버튼을 넣을 프레임
    op_frame = tk.Frame(window)
    op_frame.grid(row = 1, column = 1)

    # 연산자 버튼을 넣어봅시다.
    op_list = ['*', '/', '+', '-', '(', ')', 'C', 'AC']
    r = 0
    c = 0

    for btn_text in op_list:
        def cmd (x = btn_text):
            click(x)
        tk.Button(op_frame, text=btn_text, width = 5, command = cmd).grid(row = r, column = c)
        c = c + 1
        if c > 1:
            c = 0
            r = r + 1

    # 버튼과 표시창 연결하기 : Event Handling (Event: 사용자의 동작에 따라 발생하는 신호)
    # 프로그램은 보통 Event에 따라 동작함.
    # 메뉴를 실행하는 것부터, 키보드, 마우스를 사영하는것 그리고 우리가 만든 버튼을 누르는 것도 모두 Event.
    # Event Handling : 사용자 동작 -> 이벤트 발생 -> 이벤트 처리 -> 피드백 -> 사용자동작

    window.mainloop()

if __name__ == '__main__':
    main()
