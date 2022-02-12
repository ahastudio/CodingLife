<template>
    <div class="home">
        <WalletList/>
    </div>
</template>

<script>
  import WalletList from '@/components/WalletsList';

  export default {
    name: 'Home',
    components: {
      WalletList,
    },
  }
</script>
