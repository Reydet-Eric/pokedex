package Wild.Pokemon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import Wild.Pokemon.repository.PokemonRepository;
import Wild.Pokemon.entity.Pokemon;

import java.util.List;


@RestController
public class PokemonController {
    @Autowired
    private PokemonRepository pokemonRepository;


    //ajout d'un pokemon
   @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Pokemon pokemon) {
  
            if (pokemonRepository.existsByName(pokemon.getName())) {
            return new ResponseEntity<>("ce pokemon existe déja", HttpStatus.BAD_REQUEST);
        }
        Pokemon newpokemon = new Pokemon();

         newpokemon.setName(pokemon.getName());
         newpokemon.setType(pokemon.getType());
         pokemonRepository.save(newpokemon);
         return  new ResponseEntity<>("pokemon enregistré", HttpStatus.OK);
    } 


    //lecture des pokemons
    @GetMapping("/pokemons")
       public List<Pokemon> getAllPokemons() {
       return pokemonRepository.findAll();
       }

    //lecture d'un pokemon par son nom
    @GetMapping("/pokemon/{name}")
    public Pokemon readPokemon(@PathVariable String name) {
        return pokemonRepository.findByName(name).get();
    }

    //modification du nom d'un pokemon
    @PutMapping("/update/{name}")
    public Pokemon updatePokemon(@PathVariable String name, @RequestBody Pokemon pokemon) {
        readPokemon(name).setName(pokemon.getName());
        
        return pokemonRepository.save(readPokemon(name));
    }

    //suppression d'un pokemon
    @GetMapping("/delete/{id}")
    public String deletePokemon(@PathVariable int id) {
        pokemonRepository.deleteById(id);
        return "pokemon supprimé";
    }
}
