# Flask API 서버

기존 코드: <https://github.com/ahastudio/CodingLife/tree/main/20190618/flask>

```bash
source venv/bin/activate

pip install -r requirements.txt

pytest

FLASK_DEBUG=1 flask run

http GET http://localhost:5000/
http GET http://localhost:5000/posts
http POST http://localhost:5000/posts title=Title body=Body
```
