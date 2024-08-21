////package `api-sistema`.school.sptech.api.controller
//package ProjetoPI.ProjetoDoadores.controller
//
//import ProjetoPI.ProjetoDoadores.domain.TaxaComparacaoDoacao
//import ProjetoPI.ProjetoDoadores.repository.TaxaComparacaoDoacaoRepository
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/dash")
//class TaxaComparacaoDoacaoController(val repository: TaxaComparacaoDoacaoRepository) {
//
//    @GetMapping
//    fun listarTaxasComparacao(): ResponseEntity<List<TaxaComparacaoDoacao>> {
//        val listaTaxas = repository.findAll()
//
//        return if (listaTaxas.isEmpty()) {
//            ResponseEntity.noContent().build()
//        } else {
//            ResponseEntity.ok(listaTaxas)
//        }
//    }
//}
