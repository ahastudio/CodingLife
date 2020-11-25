<script>
	import Page from './Page.svelte';

	export let title;

	let name = '아샬';

	function handleChangeName(event) {
		const { value } = event.target;
		name = value;
	}
</script>

<div>
	<h1>{title}</h1>
	<hr />
	<Page {name} onChangeName={handleChangeName} />
</div>

<style>
</style>
