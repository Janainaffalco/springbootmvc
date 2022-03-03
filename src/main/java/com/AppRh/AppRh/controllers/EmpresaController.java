package com.AppRh.AppRh.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRh.AppRh.models.Empresa;
import com.AppRh.AppRh.Repository.EmpresaRepository;

@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository er;
	
	//cadastrar empresa
	@RequestMapping("/cadastrarEmpresa")
	public String form() {
		
		return "empresa/form-empresa";
	}
	
	//POST que cadastra funcionários
	@RequestMapping(value = "/cadastrarEmpresa" , method = RequestMethod.POST)
	public String form(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes ) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarEmpresa";
		}

		er.save(empresa);
		attributes.addFlashAttribute("mensagem", "Empresa cadastrado com sucesso!");
		return "redirect:/cadastrarEmpresa";
	}
	
	//GET que lista empresa
	@RequestMapping("/empresas")
	public ModelAndView listaEmpresas() {
		ModelAndView mv = new ModelAndView("empresa/lista-empresa");
		Iterable<Empresa> empresas = er.findAll();
		mv.addObject("empresas", empresas);
		return mv;
		
	}
	
	
	//GET que deleta empresa
	@RequestMapping("/deletarEmpresa")
	public String deletarEmpresa (String nome) {
		Empresa empresa = er.findByNome(nome);
		er.delete(empresa);
		return "redirect:/empresas";		
		
	}
	// Métodos que atualizam funcionário
	// GET que chama o FORM de edição do funcionário
	@RequestMapping("/editar-empresa")
	public ModelAndView editarEmpresa(long id) {
		Empresa empresa= er.findById(id);
		ModelAndView mv = new ModelAndView("empresa/update-empresa");
		mv.addObject("Empresa", empresa);
		return mv;
	}
	
	// POST que atualiza o funcionário
	@RequestMapping(value = "/editar-empresa", method = RequestMethod.POST)
	public String updateEmpresa(@Valid Empresa empresa,  BindingResult result, RedirectAttributes attributes){
		
		er.save(empresa);
		attributes.addFlashAttribute("success", "Empresa alterada com sucesso!");
		
		long idLong = empresa.getId();
		String id = "" + idLong;
		return "redirect:/detalhes-empresa/" + id;
		
	}
		

}
