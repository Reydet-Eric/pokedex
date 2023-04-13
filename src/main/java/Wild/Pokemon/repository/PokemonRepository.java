package Wild.Pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Wild.Pokemon.entity.Pokemon;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    Boolean existsByName(String name);
    Boolean existsById(int id);

    Optional<Pokemon> findByName(String name); 
    Optional<Pokemon> findById(int id);
}