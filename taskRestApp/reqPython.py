import requests

URL = f"http://localhost:8080/people"

data = {
        "name": "Name from python client",
        "age": 30,
        "email": "python@test.com"
    }

r = requests.post(url = URL, json = data)

if r.status_code == 200:
    print(f"Человек был успешно создан")
else:
    print(f"Человек не был создан")
