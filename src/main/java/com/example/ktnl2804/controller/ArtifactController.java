package com.example.ktnl2804.controller;

import com.example.ktnl2804.model.Artifact;
import com.example.ktnl2804.reponsitory.ArtifactRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class ArtifactController {
    @Autowired
    private ArtifactRepository artifactRepository;

    @GetMapping
    public String listArtifact(
            Model model,
            @RequestParam(defaultValue = "") String keywork,
            @PageableDefault(size = 5) Pageable pageable
            ){
        Page<Artifact> page = artifactRepository.findByNameContainingIgnoreCaseOrOriginContainingIgnoreCase(keywork,keywork,pageable);
        model.addAttribute("page",page);
        model.addAttribute("keywork",keywork);
        return "list";
    }

    // form add
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("artifact", new Artifact());
        return "form";
    }

    // form edit
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        Artifact artifact = artifactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid artifact Id:" + id));
        model.addAttribute("artifact", artifact);
        return "form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("artifact") Artifact artifact, BindingResult result){
        if (result.hasErrors()){
            return "form";
        }
        artifactRepository.save(artifact);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        artifactRepository.deleteById(id);
        return "redirect:/";
    }
}
