<script>
	export let name;
	export let onChangeName;
</script>

<main>
	<h2>Hello {name}!</h2>
	<!-- <input type="text" bind:value={name} /> -->
	<input type="text" value={name} on:input={onChangeName} />
	<p>
		Visit the
		<a href="https://svelte.dev/tutorial">Svelte tutorial</a>
		to learn how to build Svelte apps.
	</p>
</main>

<style>
	main {
		text-align: center;
		padding: 1em;
		max-width: 240px;
		margin: 0 auto;
	}

	h2 {
		color: #ff3e00;
		/* text-transform: uppercase; */
		font-size: 4em;
		font-weight: 100;
	}

	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>
