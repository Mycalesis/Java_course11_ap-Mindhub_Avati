Vue.createApp({
  data() {
    return {
      cards: [],
      clientInfo: {},
      errorToats: null,
      errorMsg: null,
    };
  },
  methods: {
    getData: function () {
      axios.get("/api/clients/current")
        .then((response) => {
          this.clientInfo = response.data;
          this.cards = this.clientInfo.cards;
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
    deleteCardConfirmation: function (number) {
      // Muestra una confirmación al usuario y, si confirma, borra la tarjeta
      if (confirm('The card will be deleted ¿Are you sure do you want continue?')) {
        this.deleteCard(number);
      }
    },
    deleteCard: async function (number) {
      try {
      console.log(this.number)
        const response = await axios.post("/api/cards",`number=${this.number}`); //
        if (response.status === 200) {
          // Borrado exitoso, actualiza la lista de tarjetas
          this.getData(); // Vuelve a cargar las tarjetas después de borrar una
          alert('Card deleted');
        } else {
          alert('Error, card cant be deleted');
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
