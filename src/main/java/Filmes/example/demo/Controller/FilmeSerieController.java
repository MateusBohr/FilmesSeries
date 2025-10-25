package Filmes.example.demo.Controller;

import Filmes.example.demo.Model.FilmeSerie;
import Filmes.example.demo.Service.FilmeSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("filme-serie")
public class FilmeSerieController {
    @Autowired
    private FilmeSerieService filmeSerieService;

    /// Mostra a página principal que visualiza todos
    @GetMapping("/listar-todos")
    public String listarFilmeSerie(Model model) {
        List<FilmeSerie> filmes = filmeSerieService.findAll();
        model.addAttribute("filmes", filmes);
        return "listar"; /// nome do html, repetir isso em todos os returns
    }

    /// Mostra a pagina de visualização especifica
    @GetMapping("/visualizar-filmeserie/{id}")
    public String visualizarFilmeSerie(@PathVariable Long id, Model model) {
        FilmeSerie filmeSerie = filmeSerieService.findById(id).orElseThrow(() -> new IllegalArgumentException("Obra não encontrada!"));
        model.addAttribute("filmeSerie", filmeSerie);
        return "visualizar-filme-serie";
    }

    /// Mostra a pagina de adicionar uma obra
    @GetMapping("/adicionar")
    public String adicionarFilmeSerie(FilmeSerie filmeSerieAtt, Model model) {
        model.addAttribute("filmeSerie", new FilmeSerie());
        return "adicionar";
    }

    /// Mostra a pagina de editar um filme
    @GetMapping("/editar/{id}")
    public String editarFilmeSerie(@PathVariable Long id, Model model) {
        FilmeSerie filmeSerie = filmeSerieService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Filme/Série não encontrado!"));
        model.addAttribute("filmeSerie", filmeSerie);
        return "editar"; // template editar.html
    }

    /// Chama o service para adicionar obra
    @PostMapping("/adicionar")
    public String salvarFilmeSerie(@ModelAttribute FilmeSerie filmeSerie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "adicionar";
        } else {
            filmeSerieService.adicionarFilmeSerie(filmeSerie);
            return "redirect:/filme-serie/listar-todos";
        }
    }

    /// Salvar alterações do formulário
    @PostMapping("/editar/{id}")
    public String salvarEdicaoFilmeSerie(@PathVariable Long id, @ModelAttribute FilmeSerie filmeSerieAtt, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editar";
        }
        filmeSerieService.editarFilmeSerie(id, filmeSerieAtt);
        return "redirect:/filme-serie/listar-todos";
    }
}
