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

        num_list = [
            '7', '8', '9',
            '4', '5', '6',
            '1', '2', '3',
            '0', '.', '='
        ]

        r = 0
        c = 0

        for btn_text in num_list:
            def cmd(x=btn_text):
                self.click(x)
            button = tk.Button(num_frame, text=btn_text, width=5, command=cmd)
            button.grid(row=r, column=c)
            c = c + 1
            if c > 2:
                c = 0
                r = r + 1

    def create_operator_buttons(self):
        op_frame = tk.Frame(self)
        op_frame.grid(row = 1, column = 1)

        op_list = [
            '*', '/',
            '+', '-',
            '(', ')',
            'C', 'AC'
        ]

        r = 0
        c = 0

        for btn_text in op_list:
            def cmd (x = btn_text):
                self.click(x)

            button = tk.Button(op_frame, text=btn_text, width=5, command=cmd)
            button.grid(row=r, column=c)
            c = c + 1
            if c > 1:
                c = 0
                r = r + 1

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
