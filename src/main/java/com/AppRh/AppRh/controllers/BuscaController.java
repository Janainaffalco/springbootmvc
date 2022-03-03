package com.AppRh.AppRh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.AppRh.AppRh.Repository.CandidatoRepository;
import com.AppRh.AppRh.Repository.DependenteRepository;
import com.AppRh.AppRh.Repository.EmpresaRepository;
import com.AppRh.AppRh.Repository.FuncionarioRepository;
import com.AppRh.AppRh.Repository.VagaRepository;


@Controller
public class BuscaController {
	
	@Autowired
	private FuncionarioRepository fr;
	
	@Autowired
	private VagaRepository vr;
	
	@Autowired
	private DependenteRepository dr;
	
	@Autowired
	private CandidatoRepository cr;
	
	@Autowired
	private EmpresaRepository er;
	
	//GET
	@RequestMapping("/")
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	//POST
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {
		
		ModelAndView mv = new ModelAndView("index");
		String mensagem = "Resultados da busca por " + buscar;
		
		if(nome.equals("nomefuncionario")) {
			mv.addObject("funcionarios", fr.findByNomes(buscar));
			
		}else if(nome.equals("nomedependente")) {
			mv.addObject("dependentes", dr.findByNomesDependentes(buscar));
			
		}else if(nome.equals("nomecandidato")) {
			mv.addObject("candidatos", cr.findByNomeCandidato(buscar));
					
			
		}else if(nome.equals("titulovaga")) {
			mv.addObject("vagas", vr.findByNome(buscar)); //estava findByNomesVaga
			
		}else if(nome.equals("nomeempresa")) {
			mv.addObject("empresas", er.findByNomeEmpresa(buscar));
			
		}else {
			mv.addObject("funcionarios", fr.findByNomes(buscar));
			mv.addObject("dependentes", dr.findByNomesDependentes(buscar));
			mv.addObject("candidatos", cr.findByNomeCandidato(buscar));
			mv.addObject("vagas", vr.findByNome(buscar));//estava findByNomesVaga
			mv.addObject("empresa", er.findByNome(buscar));//estava findByNomeEmpresa
		}
		
		mv.addObject("mensagem", mensagem);
		
		return mv;
	}

}