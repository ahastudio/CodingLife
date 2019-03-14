import tkinter as tk

NUMBER_SYMBOLS = [
    ['7', '8', '9'],
    ['4', '5', '6'],
    ['1', '2', '3'],
    ['0', '.', '=']
]

OPERATOR_SYMBOLS = [
    ['*', '/'],
    ['+', '-'],
    ['(', ')'],
    ['C', 'AC']
]

ERROR_MESSAGE = ' -> 오류'


class Buffer(tk.StringVar):
    def __init__(self):
        super().__init__()
        self.clear()

    def clear(self):
        self.set('')

    def append(self, value):
        self.set(self.get() + value)

    def delete(self, value):
        self.set(self.get().replace(value, ''))


# 참고: https://docs.python.org/3/library/tkinter.html
class Application(tk.Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.pack()
        self.buffer = Buffer()
        self.create_widgets()

    def create_widgets(self):
        self.create_display()
        self.create_buttons(0, NUMBER_SYMBOLS)
        self.create_buttons(1, OPERATOR_SYMBOLS)

    def create_display(self):
        display = tk.Entry(self, width=35, bg='light green')
        display['textvariable'] = self.buffer
        display.grid(row=0, column=0, columnspan=2)

    def create_buttons(self, column, symbols):
        frame = tk.Frame(self)
        frame.grid(row=1, column=column)
        for row, row_symbols in enumerate(symbols):
            for column, symbol in enumerate(row_symbols):
                button = self.create_button(frame, symbol)
                button.grid(row=row, column=column)

    def create_button(self, frame, symbol):
        return tk.Button(frame, text=symbol, width=5,
                         command=lambda: self.click(symbol))

    def click(self, text):
        self.buffer.delete(ERROR_MESSAGE)
        if text == "=":
            try:
                result = eval(self.buffer.get())
                value = round(result, 2)
                self.buffer.set(value)
            except:
                self.buffer.append(ERROR_MESSAGE)
        elif text in ['C', 'CA']:
            self.buffer.clear()
        else:
            self.buffer.append(text)


def main():
    root = tk.Tk()
    root.title('Calculator Ver. 1.0')
    app = Application(master=root)
    app.mainloop()

if __name__ == '__main__':
    main()
