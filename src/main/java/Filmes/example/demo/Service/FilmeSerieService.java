package Filmes.example.demo.Service;

import Filmes.example.demo.Model.FilmeSerie;
import Filmes.example.demo.Repository.FilmeSerieRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class FilmeSerieService {
    private FilmeSerieRepository filmeSerieRepo;

    public FilmeSerieService(FilmeSerieRepository filmeSerie) {
        this.filmeSerieRepo = filmeSerie;
    }

    public List<FilmeSerie> findAll() {
        return filmeSerieRepo.findAll();
    }
    public Optional<FilmeSerie> findById(long id) {
        return filmeSerieRepo.findById(id);
    }

    public FilmeSerie adicionarFilmeSerie(FilmeSerie filmeSerie) {
        filmeSerie.setId(null);
        return filmeSerieRepo.save(filmeSerie);
    }
    public FilmeSerie editarFilmeSerie(Long id,FilmeSerie filmeSerieAtt) {
        return filmeSerieRepo.findById(id).map(existing -> {
            existing.setTitulo(filmeSerieAtt.getTitulo());
            existing.setGenero(filmeSerieAtt.getGenero());
            existing.setAno(filmeSerieAtt.getAno());
            existing.setDiretor(filmeSerieAtt.getDiretor());
            existing.setImagem(filmeSerieAtt.getImagem());
            existing.setSinopse(filmeSerieAtt.getSinopse());
            return filmeSerieRepo.save(existing);
        }).orElse(null);
    }
}
