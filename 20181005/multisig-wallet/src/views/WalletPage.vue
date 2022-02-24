<template>
  <div class="wallet">
    <WalletDetail :address="address" />
  </div>
</template>

<script>
import WalletDetail from '@/components/WalletsDetail';

export default {
  name: 'WalletPage',
  components: {
    WalletDetail,
  },
  computed: {
    address() {
      return this.$route.params.address;
    },
  },
};
</script>
