# To-do app

[JavaScript 프로젝트 시작하기](https://github.com/ahastudio/til/blob/main/javascript/20181212-setup-javascript-project.md)

- 할 일 추가하기 -> POST /tasks
- 할 일 목록 보기 -> GET /tasks
- 할 일 완료 -> PATCH /tasks/{id} => { "status": "done" }
- 할 일 완료 취소 -> PATCH /tasks/{id} => { "status": "todo" }
- 할 일 삭제 -> DELETE /tasks/{id}
