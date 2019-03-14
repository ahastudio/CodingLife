import tkinter as tk


# 참고: https://docs.python.org/3/library/tkinter.html
class Application(tk.Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.pack()
        self.create_widgets()

    def create_widgets(self):
        self.create_display()
        self.create_number_buttons()
        self.create_operator_buttons()

    def create_display(self):
        self.display = tk.Entry(self, width=35, bg='light green')
        self.display.grid(row=0, column=0, columnspan=2)

    def create_number_buttons(self):
        num_frame = tk.Frame(self)
        num_frame.grid(row=1, column=0)

        symbols = [
            '7', '8', '9',
            '4', '5', '6',
            '1', '2', '3',
            '0', '.', '='
        ]
        columns = 3

        for index, symbol in enumerate(symbols):
            button = self.create_button(num_frame, symbol)
            button.grid(row=index // columns, column=index % columns)

    def create_operator_buttons(self):
        op_frame = tk.Frame(self)
        op_frame.grid(row = 1, column = 1)

        symbols = [
            '*', '/',
            '+', '-',
            '(', ')',
            'C', 'AC'
        ]
        columns = 2

        for index, symbol in enumerate(symbols):
            button = self.create_button(op_frame, symbol)
            button.grid(row=index // columns, column=index % columns)

    def create_button(self, frame, symbol):
        def handler():
            return self.click(symbol)

        return tk.Button(frame, text=symbol, width=5, command=handler)

    def click(self, text):
        if text == "=":
            try :
                result = str(round(eval(self.display.get()), 2))
                self.display.delete(0, tk.END)
                self.display.insert(tk.END, result)
            except:
                self.display.insert(tk.END, "-> 오류")
        elif text == "C":
            self.display.delete(0, tk.END)
        elif text == "AC":
            self.display.delete(0, tk.END)
        else:
            self.display.insert(tk.END, text)


def main():
    root = tk.Tk()
    root.title('Calculator Ver. 1.0')
    app = Application(master=root)
    app.mainloop()

if __name__ == '__main__':
    main()
