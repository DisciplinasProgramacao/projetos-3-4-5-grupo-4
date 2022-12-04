# Projeto 3 - Frota fase 1

## Nota: 12,4

## Comentários
	- é **totalmente** inadequado declarar uma interface só para guardar constantes.
	- interface é para padronizar comportamentos/habilidades (métodos) de classes.
	- cada uma das classes deveria ter suas constantes: encapsulamento e coesão
	- nome do arquivo em parâmetro: acoplamento em cada ocorrência. Colocar em uma constante.
	- se vai usar uma string para criação de veículo, por que não avisar na entrada de dados as entradas válidas? 
	- um monte de _sets_ sem necessidade e sem validação: atenção com isso. Destrói o encapsulamento e a segurança das classes. 
	
### Modelagem: 1,6/2 pontos. 
	
	
### Implementação dos requisitos de acordo com a modelagem: 5,6/6 pontos. 
	- Adicionar rota (com autonomia)  1,6/2: autonomia deve ser <= e não <
	- IPVA  1/1
	- Seguro 1/1
	- Outros custos 2/2
	

### Programa principal e seus requisitos: 5,2/7 pontos.
	- Carregar e salvar  1,2/2: usa nomes diferentes, ou seja, não carrega sem mudança manual. não salva rotas
	- Incluir veículo 	1/1
	- Incluir rotas em veículo 0,75/1 
	- Localizar um veículo 1/1
	- Imprimir um relatório do veículo 1,25/2: importante separar os custos em relatório
