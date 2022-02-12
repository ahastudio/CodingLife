<template>
    <div class="new-wallet-form">
        <div class="form-group">
            <label>Name</label>
            <input v-model="name" type="text" class="form-control"/>
        </div>
        <div class="form-group">
            <label>Required Count</label>
            <input v-model="requiredCount" type="text" class="form-control"/>
        </div>
        <div class="owners">
            <p>Owners ({{ owners.length }})</p>
            <p>
                <span v-for="owner in owners">
                    {{ owner.address }}
                    <button class="btn btn-dark btn-sm"
                            @click="removeOwner(owner)">
                        X
                    </button>
                </span>
            </p>
            <div class="form-group">
                <label>Add Owners</label>
                <input v-model="owner.address"
                       type="text" class="form-control form-control-sm"
                       placeholder="Address"/>
                <button class="btn btn-light btn-sm" @click="addOwner">
                    Add Owner
                </button>
            </div>
        </div>
        <div>
            <button class="btn btn-primary" @click="createWallet">
                Submit
            </button>
            &nbsp;
            <router-link class="btn btn-secondary" :to="{ name: 'home' }">
                Cancel
            </router-link>
        </div>
    </div>
</template>

<script>
  import { mapState } from 'vuex';

  export default {
    name: 'NewWalletForm',
    data() {
      return {
        name: '',
        requiredCount: 1,
        owners: [],
        owner: { address: '' },
      };
    },
    computed: mapState([
      'account',
      'walletFactory',
    ]),
    created() {
      this.reset();
    },
    methods: {
      reset() {
        this.owners = [];
      },
      addOwner() {
        if (!this.owner.address) {
          return;
        }

        const address = this.owner.address.trim();
        if (!address.startsWith('0x')) {
          return;
        }
        if (this.owners.find(i => i.address === address)) {
          return;
        }

        this.owners.push({ address });
        this.owner = { address: '' };
      },
      removeOwner(owner) {
        this.owners = this.owners.filter(i => i.address !== owner.address);
      },
      isValid() {
        return this.name.trim() &&
          this.name.length < 20 &&
          this.requiredCount >= 1 &&
          this.requiredCount <= this.owners.length;
      },
      async createWallet() {
        if (!this.isValid()) {
          return;
        }

        const factory = this.walletFactory;
        const receipt = await factory.createWallet(
          this.name,
          this.owners.map(i => i.address),
          this.requiredCount,
          { from: this.account }
        );

        const { wallet } = receipt.logs[0].args;
        alert(`Complete! - ${wallet}`);

        this.$router.push({ name: 'home' });
      },
    },
  }
</script>

<style scoped>
    .owners span {
        display: inline-block;
        margin-right: 10px;
    }

    .owners input {
        display: inline-block;
        margin: 10px;
        width: 50%;
    }
</style>
