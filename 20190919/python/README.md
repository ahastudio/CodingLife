# 공공데이터포털 오픈 API 맛보기

공공데이터포털: <https://www.data.go.kr/>

LocationIQ: <https://locationiq.com/>

```bash
python3 -m venv venv

source venv/bin/activate

pip install --upgrade pip
pip install -r requirements.txt

export ANIMAL_API_KEY=<오픈 API 키>
export LOCATIONIQ_TOKEN=<LocationIQ 토큰>

jupyter notebook
```

- <http://localhost:8888/notebooks/01-requests.ipynb>
- <http://localhost:8888/notebooks/02-xml-parse.ipynb>
- <http://localhost:8888/notebooks/03-locationiq.ipynb>
- <http://localhost:8888/notebooks/04-integration.ipynb>

## Preview

- <https://nbviewer.jupyter.org/github/ahastudio/CodingLife/blob/main/20190919/python/01-requests.ipynb>
- <https://nbviewer.jupyter.org/github/ahastudio/CodingLife/blob/main/20190919/python/02-xml-parse.ipynb>
- <https://nbviewer.jupyter.org/github/ahastudio/CodingLife/blob/main/20190919/python/03-locationiq.ipynb>
- <https://nbviewer.jupyter.org/github/ahastudio/CodingLife/blob/main/20190919/python/04-integration.ipynb>
