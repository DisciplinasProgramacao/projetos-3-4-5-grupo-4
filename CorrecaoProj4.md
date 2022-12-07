# Projeto 4 - Gestão de Frota (parte 2)

## Nota: 12,3

## Comentários
	- Diagrama atualizado alguns dias depois do 'pull'
	- Os problemas de encapsulamento com a tal IConstants:
	new Tanque((int)IConstantsCarro.TANQUE_CARRO.getValor(),Combustivel.GASOLINA);
	- Um absurdo a linha acima, que devia ser algo como
	new Tanque(Combustivel.GASOLINA) 
	- Sem arquivo inicial de testes
	- Main chamando 'km médio do veículo': já mudou faz tempo
  - Discrepância na participação em aulas. 
  - 
## Correção

### Modelagem: 1,8/2   
	- Modularização de tanque e combustível
	
### Requisitos dos veículos, de acordo com a modelagem: 7/9  
	-- problemas de modularidade: tiram pontos na última etapa 
	- Restrição de combustível: 2,5/3
	  -- sem deixar escolher o combustível ao incluir carro
	- Abastecimento e autonomia: 2,5/3 -- chamada de addRota em tanque não gera o custo
	- Custos fixos e variáveis: 2/3 -- custos do veículo devem incluir os variáveis; (feito só no stream de totalizar, sem encapsulamento)
 	
### Requisitos da empresa no programa principal: 3,5/9 
	- Quilometragem média das rotas da empresa: 2/3 -- testem. coloquei uma rota de 100 e ele aumentou uma média de 210 para 235 
	- Filtro para busca de rotas por data: 0/3  
	- Um dos dois abaixo: 1,5/3
		- Os 3 veículos que mais fizeram rotas -- exceção 
		- Lista de veículos ordenada decrescentemente por custos gerados -- veículos ok, mas dados zerados 

