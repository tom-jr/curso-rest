# Parametro require da Annotation @Autowired

Caso em um sistema tenha a regra de que uma dependência pode ou não ser instanciada tem uma 
parâmetro que do tipo boolean na annotation @Autowired para que possa se fazer essa dependência
obrigatória ou não em sua instanciação.

@Autowired(require = false) 
 Dessa forma o Bean que não estiver instanciado não apresentara erro ao iniciar o serviço
Spring web.