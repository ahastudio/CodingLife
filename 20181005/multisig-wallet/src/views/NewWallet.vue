<template>
    <div class="home">
        <NewWalletForm/>
    </div>
</template>

<script>
  import NewWalletForm from '@/components/NewWalletForm';

  export default {
    name: 'NewWallet',
    components: {
      NewWalletForm,
    },
  };
</script>
