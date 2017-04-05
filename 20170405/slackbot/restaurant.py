class Restaurant:
    def __init__(self, name, kind, address, phone, food):
        self.name = name
        self.kind = kind
        self.address = address
        self.phone = phone
        self.food = food

    def __str__(self):
        return '{name} - {kind} - {food}' \
               .format(name=self.name, kind=self.kind, food=self.food)
