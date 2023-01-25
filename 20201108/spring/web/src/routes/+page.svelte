<script>
  // TODO
  // 1. GET /tasks -> 목록으로 표시
  // 2. 입력 -> POST /tasks (할 일 추가) -> GET /tasks (목록 갱신)

  // 필요한 모듈 import

  import axios from 'axios';

  // 상태

  let tasks = [
    { id: 1, title: 'Test' },
  ];

  let title = '';

  // 상태를 바꾸는 함수

  async function loadTasks() {
    const { data } = await axios.get('http://localhost:8080/tasks');
    tasks = data;
  }

  async function addTask() {
    await axios.post('http://localhost:8080/tasks', { title });
    await loadTasks();
    title = '';
  }

  // 초기 동작

  loadTasks();
</script>

<svelte:head>
  <title>To Do App</title>
</svelte:head>

<h1>To Do</h1>

<ol>
  {#each tasks as task}
    <li>
      {task.title}
    </li>
  {/each}
</ol>

<p>
  <label for="input-title">
    할 일
  </label>
  <input id="input-title" type="text" bind:value={title} />
  <button type="button" on:click={addTask}>
    추가
  </button>
</p>
