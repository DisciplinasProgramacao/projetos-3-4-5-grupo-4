# Projeto 5 - Gestão de Frota (final)

## Nota:  14 + apresentação

**A nota final** se dará pela soma acima, multiplicada por um peso entre 0 e 1 relativo ao acompanhamento semanal do projeto. Este peso é atribuído **individualmente**. Lembre-se: não é só a entrega do produto finalizado que importa, é todo o processo de sua construção e as entregas parciais para o “cliente”
	
## Comentários -1
	- ainda pedindo km médio do veículo
	- exceção na execução de incluir rota (ver ao final)

## Correção
		
### Modelagem: 2/2   
	
	
### Persistência de dados: 4/4   
	- arquivo de teste: 1/1
	- salvar e carregar: 3/3 

### Robustez: 4/4
	- menu principal: 1/1
	- regras inválidas do projeto: 3/3 


### Padrão de projeto implementado: 5/5
	
	
### Documentação e apresentação:  5 pontos
	- nota individual de acordo com a documentação e participação do aluno nas apresentações realizadas ao longo do Projeto

### Exceção não tratada:

	``java.lang.reflect.InvocationTargetException
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at codigo.App.trataOpcoesReflexao(App.java:354)
        at codigo.App.interfaceUsuario(App.java:48)
        at codigo.App.main(App.java:32)
	Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
        at codigo.Fabricas.FabricaRota.criar(FabricaRota.java:12)
        at codigo.App.criaRota(App.java:481)
        at codigo.App.incluirRota(App.java:110)
        ... 7 more``
