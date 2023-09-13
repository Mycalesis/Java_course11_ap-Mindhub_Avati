Vue.createApp({
  data() {
    return {
      cards: {},
      numberSelected: "",
      errorToats: null,
      errorMsg: null,
    };
  },
  methods: {
    getData: function () {
      axios.get("/api/clients/current/cards")
        .then((response) => {

          this.cards = response.data;
          console.log(this.cards)

        })
        .catch((error) => {
          this.errorMsg = "Error, missing data";
          this.errorToats.show();
        });
    },
    signOut: function () {
      axios.post('/api/logout')
        .then(response => window.location.href = "/web/index.html")
        .catch(() => {
          this.errorMsg = "Error al cerrar sesión";
          this.errorToats.show();
        });
    },
    deleteCardConfirmation: function () {

      if (confirm('The card will be deleted ¿Are you sure do you want continue?')) {
        this.deleteCard(card.number);
      }
    },
    deleteCard: async function () {
      try {
        console.log(this.numberSelected);
        const response = await axios.post("/api/cards", `number=${this.numberSelected}`)
        ;
        if (response.status === 200) {

          alert('Card deleted');
        } else {
          alert('Error, card cant be deleted').then(response => window.location.href = "/web/cards.html");
        }
      } catch (error) {
        console.error('Error, card cant be deleted', error);
        alert('Error, card cant be deleted');
      }
    },

  },
  mounted() {
    this.getData(); // Obtén los datos al cargar la página
  },
}).mount('#app');
