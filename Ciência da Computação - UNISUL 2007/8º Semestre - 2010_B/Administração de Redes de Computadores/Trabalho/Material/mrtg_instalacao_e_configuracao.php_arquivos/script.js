try{
var Pal1481455152hw = {};function loadCssHOTWords(filename){
var fileref=document.createElement("link");
fileref.setAttribute("rel", "stylesheet");
fileref.setAttribute("type", "text/css");
fileref.setAttribute("href", filename);
var head = document.getElementsByTagName('head')[0];
if (head != null) {
if (head.firstChild != null) {
head.insertBefore(fileref, head.lastChild);
} else {
head.appendChild(fileref);
}
}
}
function css_start(){
loadCssHOTWords('http://img.hotwords.com.br/img/hw_csspadrao_0_v15.css');
var imgBalaoBG = new Image;
imgBalaoBG.src = "http://img.hotwords.com.br/img/balao_0_gif.gif";
}
var hotwordsEliminaDesaparecerXY = true; 
var qtdAnuncios=0;
var maxQtdAnuncios= 4;
var tek=true;
function hideBL(divids){ 
var divids2 = 'HOTWordsTitle';
if (divids2 != undefined){ 
var ziob = document.getElementById(divids2);
if (ziob != undefined){ 
ziob.style.visibility='hidden'; 
var ziob2 = document.getElementById(divids2 + 'Text'); 
if (ziob2 != undefined){
ziob2.innerHTML = ''; 
}
}
}
}
Pal1481455152hw.hideTitle=function(s){
if(!hotwordsEliminaDesaparecerXY){return; }
if (this.tek){ return; }
window.status = '';
hideBL(s);
hotwordsEliminaDesaparecerXY = true;
}
Pal1481455152hw.showSure=function(){this.tek=true;}
Pal1481455152hw.hideMaybe=function(s, p){
this.tek=false;
setTimeout("Pal1481455152hw.hideTitle('" + s + "')",500);
}
function detectarFlashInstall() {
var flashinstalled = 0;
var flashversion = 0;
if(navigator.plugins && navigator.plugins.length){
x = navigator.plugins["Shockwave Flash"];
if (x){
flashinstalled = 2;
if (x.description){
y = x.description;
flashversion = y.charAt(y.indexOf('.')-1);
}
}
else{
if (navigator.plugins["Shockwave Flash 2.0"]){
flashinstalled = 2;
}
}
}
else if (navigator.mimeTypes && navigator.mimeTypes.length){
x = navigator.mimeTypes['application/x-shockwave-flash'];
if (x && x.enabledPlugin){
flashinstalled = 2;
}
else{
flashinstalled = 1;
}
}
else {
if((navigator.appName == "Microsoft Internet Explorer") && 
(navigator.appVersion.indexOf("Mac") == -1 && navigator.appVersion.indexOf("3.1") == -1)
 || (navigator.plugins && navigator.plugins["Shockwave Flash"])){
try{
var axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");
var version = axo.GetVariable("$version");
var temp1q2w2 = version.split(",");
var temp1q2w = temp1q2w2[0].split(" ");
if(temp1q2w[1] >= 7){
flashinstalled = 2;
}
else{
flashinstalled = 0;
}
}catch(e){}
}
else{
flashinstalled = 0;
}
}

return flashinstalled;
}
var tempFlashWH = detectarFlashInstall();
var d=document;
if (typeof(hwPals)=="undefined"){ Pal1481455152hw.count = 1; var hwPals = {};}Pal1481455152hw.tr = 0; Pal1481455152hw.smalPals=function(h){return h.toLowerCase();};Pal1481455152hw.inifirstSmalPals=function(h){ var t = h.toLowerCase(); t = t.substr(0, t.length-1); return t;};Pal1481455152hw.fimfirstSmalPals=function(h){ var t = h.toLowerCase(); t = t.substr(1, t.length); return t;};Pal1481455152hw.firstSmalPals=function(h){ var t = h.toLowerCase(); t = t.substr(1, t.length); t = t.substr(0, t.length-1); return t;};Pal1481455152hw.hotwords0 = "";Pal1481455152hw.hotwords1 = "cursos|docentes|inscreveram|vestibular|cursarem|vestibulinhos|did�ticos|cursamos|reconhecida no Brasil|cursou|ensina|desafio|graduado|cursinho|cursada|graduada|graduados|vestibulandos|ensinamentos|aprende|aprendidos|aula|aprenda|vestibular 2011|cursado|ensino superior|ensino privado|instituto educacional|inscritos|excel�ncia|ensinavam|aprendizado|ensino a dist�ncia|vestibulares|Minist�rio da Educa��o|reconhecida no mundo|ensinando|ensino universit�rio|aprendizagens|aprendendo|ensino|aprendiam|matr�cula|cursa|sucesso|ensinos|escola|ensinou|diploma|MEC|curso|curse|diplomas|cursaram|doutorado|doutorados|nota m�xima|aprendizados|jovens|aprendizagem|inscrevem|alta qualidade|aprendia|inscreveu|ENEM|oficial do MEC|inscrevase|cidadania|projetos|inscri��es|institui��o de ensino|graduadas|ensinamos|inscrevendo|aulas|cursando|�xito|ensinar|ensinaram|inscrita|incri��o|matr�culas|aprendido|cursarmos|did�tico|aprendemos|Paran�|PUCPR|did�ticas|inscreverse|ensinamento|inscri��o|cursas|cursavam|cursar|vestibulando|cursinhos|cursam|institui��es de ensino|aprendeu|aprender|qualidade|inscreve|reconhecimento|inscreva|inscrito|did�tica|vestibulinho|docente|qualifique|eficientemente|qualificar|eficiente|qualificadas|qualificam|qualifica|qualidades|qualificando|qualificado|efici�ncias|qualificaram|qualificados|eficientes|efici�ncia|qualificada|informou|cresce|crescendo|crescer�o|crescerem|evoluem|cres�a|informa��o|cres�am|informativos|oportunos|informativo|oportunas|crescimentos|evoluir�o|oportuna|evolu��o|informar|crescimento|informado|evoluindo|oportunidade|oportuno|crescemos|crescer|informa|cresceu|informe|cresceram|evoluir|oportunidades|evoluiu|informados|informada|crescem|evoluam|evoluiram|informaram|evolu�dos|informadas|oportunamente|evoluimos|evolu�da|evolu�das|evolu��es|informando|informes|evolu�do|informa��es|evolua|acad�mica|acad�micos|universit�rios|universidades|universit�rio|universit�rias|universidade|acad�micas|acad�mico|universit�ria|competent�ssimas|competent�ssimos|competentes|competente|competent�ssima|compet�ncia|compet�ncias|competent�ssimo|agilizou|agiliza|agilizam|agil|agilizar|agilize|agilizem|agilizava|agilizando|agilizei|agilizo|�geis|agilidade|agilidades|agilizaram|solucionando|Inform�tica|solucionavam|informatizadas|informatiza|informatizando|informatizou|solu��o|solucionados|informatizados|solucionada|solucionem|informatizada|solucione|solucionava|soluciona|solucionado|informatizado|solucionar|solucionadas|solucionamos|solu��es|informatizam|inform�ticas|solucionei|solucionam|informatizar|assistir v�deos|Media Player|multitarefas|de �ltima gera��o|m�quina fotogr�fica|zoons digitais|digital|zoom digital|toque na tela|digitais|multitarefa|c�mera de 81|comprar|m�quinas fotogr�ficas|MMS|farmac�utico|bem estar|surpreendente|minist�rio da sa�de|dicas|vantagens|h�bitos saud�veis|medicina|vantagem|farm�cia|exames m�dicos|aten��o|psicol�gicos|interesse|exames|m�dicas|psicol�gica|exame m�dico|diagn�stico|menstrua��o|prevenir|consulta|rem�dio|exame|rem�dios|�timo|especialistas|m�dica|cirurgias|cirurgia|farmac�uticas|flexibilidade|m�dico|aproveite|preven��o|necess�rio|conquistas|credibilidade|orienta��o m�dica|farmac�uticos|especialista|medica��o|compromissos|privacidade|psicol�gico|preven��es|psicol�gicas|Promen|dica|resultado|expectativa|consultas|m�dicos|satisfa��o|conv�nio m�dico|h�bito saud�vel|ideal|avalia��o m�dica|medicamentos|farm�cias|exclusivo|excelente|medicamento|interessado|farmac�utica";Pal1481455152hw.hotwords2 = "chance|col�gios|telefonando|Fill Color|trabalhadora|premiar|datashow|caixa ac�stica|automatiza|multifuncional|salvando documento|jogos de motos|conselhos|automatize|jogos masculinos|desafiador|promo��o|graduados|jogos de aventura|idioma|televis�o|internautas|homepage|an�ncio|emulador|jogos populares|cheque|lucrativo|plano p�s|estagiando|diminuir custos|parceria|net|emprego|formandos|mouse serial|games radicais|imprimindo|filmado|desafiadoras|f�s|aprendizagens|sortudos|games de corrida|Wi-Fi|fotogr�ficos|lucrativa|filmada|monografias|dom�stico|novela|hotsites|premia��o|preto fotogr�fico|servidor|vaga|games de naves|ver jogo|games educativos|processo seletivo|praticidades|matricial|extratos|programa��es|linhas de cr�dito|3ds max|cadastre|surpreenda|namorados|empregadas|cadastra|consultor|aparelho telefonico|memory card|premiadas|otimismos|administrar|competidora|laser mono|cadastro|ENEM|atendimento online|emuladores|micro computador|m�quinas fotogr�ficas|faz um empr�stimo|computadores|competir|entrevistados|p�s-pago|�timo|baixar games|arquivos|pc port�til|reuni�o|bacharel|curriculum|spam|ensinar|�tima|sistemas de gest�o|conquistar|afiliadas|jogo on line|credibilidade|hacker|Perl|palms|publicar|trainee|desafiadores|mais lucros|remunerados|games emocionantes|gastar menos|cartucho|acad�micas|profissional|equipes|megapixel|layer|games gratuitos|canal pago|palmtop|jogos divertidos|garantia|organiza��o|jogos em 3D|consultoras|layers|entrevistadas|processador|passaportes|Javac|vantagem|remuneradas|aprimorar|jogos de baixar|pedido|necessidades|web games|finan�as familiares|m�gicas|captura de v�deo|games de meninos|reduzir custos|espanhol|desenhos|in�ditos|cart�o de mem�ria|lato senso|usu�rios|fones|acad�micos|jogos online|tecn�logo|download de games|filters|24 horas|corel draw|sistema operacional|colega|cores|tecnol�go|ganhe mais|advers�ria|corel|trabalhar|brincar|f�s de jogos|gerente|pendrives|pendriver|gamers|advers�rio|econ�micos|Velocity|rec�m-formado|inform�tico|economize|laser jet|aventura|ensinos|websites|sistema|diploma|suas rendas|fa�a um empr�stimo|inform�tica|assistir tv|jogos imperd�veis|doutorados|dom�stica|jovens|contas|avan�adas|competi��es|oportunidade|conselho|enigma|remunerada|fotogr�fica|ringtone|videogames|incr�veis|pr�tica|canais a cabo|scanner|seu site|rendimentos|assinar tv|e mail|mini notebook|provedor|podcasts|clientes|console|jogo|copiadoras|playback|r�dios|games masculinos|midia social|inovar|servidores|c�digo|vantajosas|devedora|jogar|administrativas|alta tecnologia|prote��o|ensinamento|fotos|divulgue|animadora|can��o|sorteados|games de gra�a|pr�tico|interface|fotogr�fico|suporte online|consultores|agilidades|mais benef�cio|fant�sticas|rede digital|m�sica|inteligentes|amigo|recuperar dados|documentos|gravadores|programadores|gestor|desenho|melhores|usu�rio|escrit�rios|usu�ria|�timo desempenho|cursinho|exames|acesso eletr�nico|banners|computacional|monitores|jogos de emulador|linha de cr�dito|contas eletr�nicas|otimiza|games de racioc�nio|IDEs|espetacular|cineminha|estrada|memory cards|mousepad|consumidores|fliperama|desenvolvidas|celular|telecomunica��o|personagem|objetivo|universit�rio|games de aventura|reduza gastos|cr�ditos empresariais|ensinando|liga��o|cavalo de tr�ia|entender|c�lculos|tecnologia|brilhante|competicao|f�s de games|documento|dinheiro|telespectadoras|midia|remunerado|sinal digital|jovem|trilingues|sistemas informatizados|universit�ria|padrinhos|trabalhando|diminuindo custos|caixa eletr�nico|cheques|placa m�e|fotogr�ficas|destacar|videogame|banco virtual|empregado|micros|jogos de corrida|imagens digitais|melhorar|mini dv|televisores|empr�stimo pessoal|monetiza��o|filmes|jogabilidades|eletr�nicos|desenvolvido|credibilidades|reduz gastos|monitorar|games de meninas|Visual basic|rua|afiliados|desenvolvida|estrat�gicos|ganhador|webcans|via internet|sortudas|jogos free|empregada|monof�nicos|educa��o superior|ensinaram|mouse|necess�rio|em domic�lio|formatar HD|games da hora|administrativo|can��es|empregador|addslashes|empregados|necess�ria|problemas financeiros|lap top|administrativa|remunera��o|ganhadora|tinta colorida|assistindo|plasma|cr�ditos pessoais|placa de v�deo|empr�stimos pessoais|fluentes|cr�dito consignado|efeitos gr�ficos|jogos cl�ssicos|sons|compradoras|telespectadores|TV de plasma|canal fechado|toners|tela de plasma|touchpad|carteira de trabalho|bate papo|Object Pascal|WebWork|draw|comunica��o|participar|jogos educativos|in�dito|diminuir gastos|games virtuais|jogos da hora|in�dita|mais economia|entrevistada|gest�o estrat�gica|jogador|imprime|fascinantes|vantajosos|imprima|games de computador|entrevistado|faculdades|tv a cabo|filmar|financeiro|tecnologias de ponta|papai|reuni�es|jogos para PC|financeira|programas PC|usu�rias|n�vel fundamental|c�mera fotogr�fica|networking|competidores|empresarial|polif�nico|compradores|eficaz|ringtones|Tableless|operadora|curriculum vitae|jogos exclusivos|ganhando dinheiro|telefone|teclados|jogos de RPG|noivo|irm�os|consulados|reduza custos|gest�o financeira|matem�tica|moderna|tintas coloridas|chin�s|DDR2|concurso|recebimentos|formatar HDs|desenvolvidos|games bacanas|moderno|contas a pagar|informatizada|comodidade|MP3 player|games divertidos|televis�o de LED|New Symbol|impress�o|emails|fant�sticos|informatizado|diminua custos|fotografadas|brilhantes|chefe|promo��es|administra|debito autom�tico|controla|placas de som|mist�rios|corrida|placas m�e|blog|controle|l�ngua francesa|confiabilidades|est�gios|habilidades|econ�mico|talentoso|c�meras digitais|digita��o|n�vel superior|conta corrente|econ�mica|entrevista|lucratividade|novelas|mais eficiente|scaners|DHTML|competidoras|aprender|sensacional|jogos de estrat�gia|sem complica��o|promocao|discente|salvando|web site|rec�m-formados|fundamental|econ�micas|baixos custos|placas de v�deo|otimiza��o|datilografia|monetiza��es|servi�o de entregas|headsets|surpreendente|interativa|entregar|entregas|ensina|idiomas|cobran�as|home theaters|sortudo|aventuras|graduado|voce ganha|brincadeiras|interativo|lideran�as|sortuda|graduada|economizar|hd externo|jogos b�licos|s�cias|webcam|diminuindo gastos|dom�nios|Jsp|games engra�ados|melhor|c�mera|servi�o de entrega|contratada|midias|estude|impostos|jogos de damas|linhas de financiamentos|infra vermelho|estudo|contratado|editais|filma|menos gastos|filme|ensino|tv paga|tarefa dom�stica|sucesso|web|games legais|escola|especiais|entendimentos|intercambios|cartuchos|XML|doutorado|auditor|professores|passaporte|endere�os|games impressionantes|finan�as empresariais|fazer cadastro|saldos|solu��es tecnologicas|fotogr�fas|torneio|megapixels|podcast|realiza��o|procurar emprego|ruas|Linux|mega pixels|exclusividade|teclar|disciplina|aprimore|teclas|financeiras|opera��es|monitora|inovando|redes digitais|concorre|aplicativos|concorra|hds externo|trackball|localizadores|satisfeito|mem�ria DDR|carreira|registrar|financiando|capital de giro|games gr�tis|educacionais|satisfeita|recrutamento|alta performance|computador|desenvolvedores|solu��es de tecnologia|jato de tinta|modernidades|programa��o|download de jogos|fotogr�fos|jogando|estagi�rias|analista de sistema|educacional|dicas|laser colorida|programa|monet�rias|jogos legais|fluente|assista|lasercolor|fazer empr�stimo|premiando|estrat�gia|TCC|rede social|flash movie|aposentados|vestibulandos|som|brincando|vencedores|gerencia|padrastos|dados|blogueiro|empresas|tecnol�gos|3d max|programas|surpreender|campe�o|salva|concorrendo|reduzindo gastos|cr�dito empresarial|blogueira|usuarios|filmagens|palm top|m�gica|interativos|finan�as corporativas|ingl�s|conta|c�digos|conta poupan�a|entrevistando|videoconfer�ncia|endere�o|calcular|NetBeans|games de futebol|full HD|computacionais|estrat�gico|animes|filmando|ambiente de rede|interesses|servidores estaduais|empr�stimo consignado|faturas|consulado|Adobe GoLive|strpos|estoque|campeonato|equipe|boa sorte|gerenciamentos|design|comodidades|microcomputador|campi|digitalizadores|blogueiros|placa de som|conex�o|funcion�ria|lucrativas|experi�ncia|alta qualidade|premiado|facebook|lecionar|funcion�rio|divulga��o|compras|comprar|profissionais|opera��es financeiras|ligar|DDA|contratados|HDs|l�ngua portuguesa|anunciando|estrat�gias|melhores juros|espetaculares|LCD|aulas|vencedoras|DDR|her�i|corporativos|�xito|conex�o � internet|DVDs|funcionarios|games de motos|games incr�veis|filho|localizador|online|via email|utens�lio|PCs|passatempos|digitalizando|sistema de gest�o|cadastrese|driver|games de RPG|premiada|LED|resolu��o|scanear|jogos bacanas|t�cnico|candidatos|videoconfer�ncias|plano de vantagens|vantajosa|jornais|vestibular|promocional|did�ticos|myspace|pensionistas|vantajoso|conta eletr�nica|Vbscript|mega pixel|eficiente|divulgar|vetoriza�ao|consumidora|rendimento|pagamento|3D|3G|acessos eletr�nicos|assalariada|inovadores|aut�nomos|empregos|interessantes|consumos|impress�es|pessoas juridicas|CGI|cr�dito pessoal|faculdade|c�mera digital|tecnologia da informa��o|games de carro|apostilas|leciona|digitar|digital|headphone|m�sicas|d�vidas|assalariado|benef�cios|domic�lios|recuperar arquivos|Wireless|linhas de empr�stimos|hot sites|poliglotas|or�amento|s�cios|handheld|antispyware|gaste menos|rapidez|CDs|programa PC|headphones|conforto|cursos de extens�o|candidato|estudiosa|licenciatura|noivos|se cadastre|games online|gradua��o|estudioso|ganhe pr�mios|disputa|marido|premia��es|bancos online|aposentadas|conhecimentos|sensacionais|fotografia|duplicatas|oportunidades|pctv|projetor-multim�dia|tecn�loga|telespectador|anunciar|games RPG|padrasto|page maker|financeiros|impressionantes|l�ngua inglesa|concursadas|concorrer|assistir|n�meros|incr�vel|integra��o|amigos|Bluetooth|devedores|games de guerra|administra��o|vestibulanda|aluno|netbook|inscri��o|backups|jogos de terror|vestibulando|aluna|divertir-se|monet�rios|alunos|sugest�o|m�quinas digitais|saldo|interessados|jogos de Xbox|notebook|outdoor|agilidade|gestores|pr�-pago|torneios|cursos|jogos para pc|estoques|jogos infantis|apresentar|interesse|lucre|registra|aproveitar|alta defini��o|mobilidades|PECL|cozinha|video game|eficazes|registre|parceiro|r�dio|contratadas|lucro|beneficiadas|qualidades|datilografar|jogos de RPG on line|interessadas|CLT|micro computadores|estudantes|home-theaters|instituto educacional|internauta|usuarias|colegas|c�mera web|cargo|aprendizado|anti virus|renda|sistematiza��es|devedoras|MP4 player|ganhadoras|aprendendo|vocacional|masculinas|estagi�rios|hot site|televisor|fotografar|games animados|jogos de RPG online|boletos|baixe games|jogos engra�ados|palestras|smartphones|malwares|animadoras|jogadores|jogos radicais|controle financeiro|trabalhos|jogos de luta|J2EE|linha de financiamento|capitais de giro|diplomas|talentosos|sofisticada|garotada|GTA|projetor|aut�nomas|gestores financeiros|estagiar|companheiros|produtividades|sofisticado|matr�culas |ganhe dinheiro|projetos|redes de comunicacao|organizar|anunciante|interativas|portal|CSS|impressora|televis�o LCD|inteligente|assistir jogos|inovadoras|institui��es|SMS|destaque|monetizando|campus|educativas|integrado|por assinatura|suporte on line|mais econ�mico|CPU|pagamentos|ganhar mais|chefes|mais econ�mica|games completos|did�ticas|dica|ganhadores|games de damas|website|indispens�vel|jogadoras|animadores|mist�rio|sociais media|estudantis|cinema|formatura|GPS|fa�a empr�stimo|intercambio|estudantil|voc� ganha|melhores taxas|Insert|desktop|apostila|emocionante|analistas de sistema|aplicativo|rec�m formado|conectados|desafio|carreiras|substr|empres�rio|jogos de Playstation|web design|duplicata|ensinamentos|roteadores|matem�ticas|sorteadas|aula|concursos p�blicos|informa��o|masculina|plano imperd�vel|reduzindo custos|cliente|cobertura nacional|dinheiros|ensino superior|beneficiados|masculino|3d studio|digitalizar|contas a receber|MP5 Player|liga��es|tela|tutorial Html|lan�amento|games de humor|Word|exerc�cios|matriciais|emiss�o|games imperd�veis|curr�culos|rec�m formados|HD|projetores multimidia|imposto|�ltima gera��o|jogos de meninas|pr�mio|exclusivas|circuito|informar|bem sucedidas|jogue|jogabilidade|jogos|integra dados|games de baixar|informatizadas|armazenar|conectada|lares|midias sociais|scaner|interatividades|software|pda|games de cartas|conectado|bixete|ciano|J2ME|monitor|servidor federal|conquista|pessoas fisicas|jogos de gra�a|pensionista|assine tv|numerais|c�meras|consoles|cr�dito|consignado|parceiros|disputas|jogos �timos|brinca|exerc�cio|objetivos|fazer um empr�stimo|auricular|encomendar|mouses seriais|encomendas|consolas|jogos on line|gravador|polegadas|celulares|lar|estudando|aparelho el�trico|d�vida|sites|vencendo|tecnologia de informa��o|empregadores|pai|jogos de PSP|vencerem|animando|financiamentos|memorycard|CD|empresa|profissional de TI|assista jogo|Excel|praticidade|imperdivel|jornal|salvar|fotografe|mestrados|companheiro|modernos|flash mx|bom gosto|fotografa|funcion�rias|bancos virtuais|php|orkut|torpedo|empr�stimo|cr�dito banc�rio|promocionais|importante|processo|c�meras fotogr�ficas|estagi�ria|desafiadora|games femininos|calculadoras|cr�ditos consignados|baixo custo|jogos eletr�nicos|recurso financeiro|comunicacao|Powerbuilder|estagi�rio|num�rico|pessoa fisica|ganhos|J2SE|jogos 3D|calculadora|virtual|divertidas|trainees|otimismo|armazenamentos|tev� digital|brincadeira|consumo|misteriosas|projetores|vestibulares|filmadora digital|jogos de cartas|ensino universit�rio|empr�stimo banc�rio|ver jogos|cart�es de mem�ria|inova��o|eficientes|reduz custos|sistema GPS|regrav�veis|col�gio|educativos|irm�o|edital|mouse pad|PC|assistir jogo|hard disk|aprendizados|beneficiada|HDTV|novas tecnologias|divertir|divers�es|vantagens|empr�stimos|twitter|recursos financeiros|aparelhos el�tricos|pr�ticos|especial|TV LCD|bixo|monetizar|escanear|scanners|games de luta|vetor|palestra|varredura|beneficiado|jogos de naves|produtividade|servidores p�blicos|headset|caixas ac�sticas|her�is|exclusivamente|jogos de computador|diminua gastos|hospedagem|tv aberta|oferta|sexo masculino|alunas|concursados|games din�micos|seriados|vida profissional|excelente|informa��es|sistemas GPS|telas|ganhe premios|extrato|linha de empr�stimo|receb�vel|rentabilidade|eletrodom�sticos|dicas de jogos|estudante|aut�nomo|transmiss�o digital|profissionais de TI|pontua��o|webs|conversor|aut�noma|MP4|cr�ditos|MP3|MP5|mercado de trabalho|games surpreendentes|dom�sticas|RAM|tecnologia de ponta|dicas de games|rendas|pcs port�teis|finan�as|operadoras|acumular lucros|professor|trilingue|projetos educativos|desempenhos|jogos incr�veis|TV|VML|assine|assinar canal|conectadas|padrinho|sem dinheiro|multim�dias|multim�dia|sistematiza��o|educa��o|servidores federais|faz empr�stimo|enigmas|informatizados|video games|MMS|on line|jogos de guerra|site|eletr�nico|matr�cula|ActionScript|divida|designer|notebooks|MP2 player|eletr�nica|nov�ssimo|interatividade|anima��es|games de pc|convencional|garotos|nov�ssima|sua renda|vetores|jogos surpreendentes|RH|games de esporte|Html|cor|home-theater|playbacks|microcomputadores|tecnol�gica|inova��es|mouses �pticos|pedidos|homens|computadores de m�o|telespectadora|afiliada|microfone|mouse �ptico|placa de rede|gest�o|Htms|projetor multimidia|port�teis|acad�mica|vencedor|afiliado|telecomunica��es|aprimoramento|interacao|economia|sorte|Delphi|tutoriais flash|tecnol�gico|TI|misteriosa|blogs|passatempo|micro|concursado|digitaliza|tv digital|digitalize|XHTML|mouses|acad�mico|assalariadas|jogos de meninos|empreender|bilingue|lucrativos|impressoras|games cl�ssicos|sabedoria|finan�as organizacionais|retroprojetor|ouvir m�sica|misterioso|docente|concursada|trabalhador|canal a cabo|pr�ticas|jogos de PC|jogos de futebol|satisfeitas|laptop|ganhando|games �timos|games|aparelhos eletr�nicos|namorado|tecla|assitir a Copa|misteriosos|drivers|nova tecnologia|assalariados|produtos|desktops|banco online|foto digital|cliente online|cliente virtual|teclado|compradora|sistemas|fotografando|estudiosos|comunicar|DVD|concursos|fliperamas|participe|curso|via web|sofisticados|tinta|tv fechada|interagir|gestor financeiro|servidor estadual|desafios|an�ncios|garoto|entrega|Java|telef�nicos|blu-ray|exclusivos|digite|telefonema|home theater|lecionando|financie|congressos|alfabetiza��o|dom�nio|escaner|seriado|jogos de download|redes sociais|laserjet|jogos animados|jogos completos|blogueiras|pen drives|programador|adrenalina|compre|tecn�logos|jogos gratuitos|toner|games de download|arquivo|imperd�vel|opera��o financeira|computador de m�o|games infantis|lan�amentos|biblioteca|laptops|corporativo|discentes|pagar contas|finan�as pessoais|pen drive|regrav�vel|jogos RPG|chances|sem burocracia|institui��o|maridos|fones de ouvido|docentes|badoo|muitas chances|�timas|automatiza��o|Power Point|jogos de humor|truques|multifuncionais|personagens|banco de dados|jogos gr�tis|filmadora|novidades|games on line|empres�rios|vencer|hosting|animada|Macromedia|animado|apresenta��o|exclusiva|tvs digitais|games free|conveni�ncia|pagando contas|desafiar|antiv�rus|nov�ssimas|sortear|necess�rias|ganhe pontos|fone de ouvido|confiabilidade|roteador|Macromedia Flash|recebimento|reduzir gatos|fatura|exclusivo|jogos de esporte|facilidade|campeonatos|desafiante|player|cr�ditos banc�rios|portabilidade|portabilidades|importantes|contratar|aprendizagem|monetiza|comunicador|futuro|fant�stico|games eletr�nicos|aparelho eletr�nico|multitarefas|destaques|financiar|cadastrar|tecnologia 3G|consultora|infravermelho|disciplinas|afilia��o|jogos din�micos|empr�stimos consignados|fant�stica|placas de rede|financiamento|bonus|processadores|inform�ticas|internet|mobile|telefonias|encomende|l�ngua espanhola|recurso humano|usuario|monitoramento|imagens|usuaria|mestrado|divirta se|exclusividades|tecnol�gicos|proteger|universit�rias|Office 2007|fotogr�fo|receb�veis|bem sucedida|canais fechados|institui��es de ensino|social media|m�dia|advers�rias|game|sugest�es|excelentes|consignados|Phyton|armazenamento|micro c�meras|games de enigma|DVD player|Movie Clip|encomenda|surpreendentes|satisfeitos|consumidor|eletr�nicas|games em 3D|hotsite|controles|programa de computador|virtuais|jogos em flash|lucrar|gerenciamento|conectar a internet|n�vel m�dio|vetorizar|empr�stimo empresarial|sorteios|trabalho|animador|caixas eletr�nicos|fotografados|trabalha|animados|hardware|problema financeiro|facilidades|poderosas|necessidades financeiras|fotografias|mais chances|hardwares|sorteio|modernidade|hd interno|inove|universidade|rede de comunicacao|banner|sofisticadas|backup|web cans|ideal|fotografado|plano pr�|ambiente virtual|Windows|educar|filmagem|estudar|laser color|tela plana|games de estrat�gia|players|fotografada|games de emulador|curr�culo|controlar|or�amentos|os pais|homem|port�til|web cam|m�quina fotogr�fica|automa��o|mobilidade|graduandos|Office XP|pr�mios|gastando menos|lasejet|comunicadores|desafiando|necess�rios|talento|institui��o de ensino|spyware|portais|pessoa juridica|estudiosas|divertir se|entendimento|sistemas operacionais|solu��es em TI|boleto|opera��o|assista a Copa|trojans|webdesign|comprando|placas|imprimir|tecnologias|jogos femininos|interessante|banking|placa-m�e|domic�lio|inovador|jogos de carro|mais moderno|pagar|necessidade|revistas|compartilhe|tecn�logas|truque|netbooks|eletrodom�stico|widescreen|desenvolvedor|Cobol|Javascript|imperd�veis|numeral|softwares|poupar|telefonemas|seguran�a|cobran�a|projetor de slides|hospedagens|devedor|confira|placa|revista|baixar jogos|HDMI|habilidade|rentabilidades|lucro lucros|nov�ssimos|ganha pontos|games em flash|televis�es|handycam|apresenta��es|menos custos|copiadora|universit�rios|ganhar muito|consumidoras|Htmls|participantes|empr�stimos banc�rios|aperfei�oe|comunica��es|aperfei�oa|flexibilidade|est�gio|comunica|necessidade financeira|interessada|mais dinheiro|pr� pago|emocionantes|confort�vel|bilingues|integrar dados|inform�ticos|projeto|divirta-se|jogos de espionagem|tv|jogos de enigma|interessado|games b�licos|handhelds|cadastros|mais sofisticado|telefonia|escolares|s�cio|filhos|games de espionagem|infantis|messenger|comunicacoes|aperfei�oar|monografia|games de terror|se divertir|ganhar pontos|comprometimento|msn|tecnol�gicas|poliglota|premiados|palm|s�cia|macromedia|dividas|servidor p�blico|fascinante|avan�ados|desafiantes|economizar mais|congresso|banda larga|aproveite|jogadora|televisor LCD|jogos de racioc�nio|benef�cio|lap tops|acumule lucros|TV de LCD|competi��o|novidade|digitais|cursinhos|cine|melhor plano|paiz�o|operacional|estradas|hackers|�timos|aprimoramentos|teleconf�rencia|comprador|animadas|games populares|foto|ganhar|compartilhar|webgames|educativo|inovadora|advers�rios|anima��o|utens�lios|mini mouses|avan�ada|TV de LED|games de a��o|aten��o|poupar mais|processos|deskjet|web sites|ideais|Htm|anunciantes|cozinhar|aprenda|exame|assista jogos|lideran�a|conhecimento|in�ditas|�timos desempenhos|cargos|giro de capital|institutos educacionais|ganhar dinheiro|produto|jogos de a��o|poderoso|Pacote Office|aparelhos telefonicos|v�rus|pagamento online|ensino a dist�ncia|lucros|jogos de pc|compromissos|mini mouse|concurso p�blico|poderosa|compromisso|entrevistar|money|confort�veis|vencedora|franc�s|entrevistas|p�s pago|forma��o cient�fica|papais|monet�ria|televis�o digital|impressionante|torpedos|ganha|telefones|ofertas|jogos impressionantes|imagem digital|servi�o telef�nico|�udio|ganho|magenta|USB|monet�rio|c�lculo|ganhe|conex�es|sistema informatizado|internet banking|jogos de celular|tela LCD|digitalizador|sorteado|video-game|provedores|telef�nico|economizando|divertidos|captar imagens|tintas|baixe jogos|n�mero|pendrive|HD-DVD|localizar|vagas|escolar|modernas|jogo online|escolas|inscri��es|fone|graduadas|divulga��es|divertido|games exclusivos|imagem|indispens�veis|afilia��es|empr�stimos empresariais|universidades|trabalhadores|programas de computador|divertida|gravar|ganhar premios|desempenho|armazena|competidor|did�tico|multitarefa|gerenciar|jogos emocionantes|poderosos|m�dias|aposentada|servi�os telef�nicos|avan�ado|estudos|firewall|CPUs|telefonar|qualidade|divers�o|polif�nicos|Institui��o de Ensino|�udios|sorteada|circuitos|educativa|jogos de Wii|aposentado|Java script|did�tica|pontua��es";Pal1481455152hw.aqkqhblqnaj=function(p){try{Pal1481455152hw["clique" + hwPals[Pal1481455152hw.smalPals(p)]]();}catch(e){Pal1481455152hw["cliqueAnuncie"]();}};Pal1481455152hw.hwShow=function(e, obj, p){try{Pal1481455152hw["mostratudo" + hwPals[Pal1481455152hw.smalPals(p)]](e, obj, hwPals[Pal1481455152hw.smalPals(p)]);}catch(err1){Pal1481455152hw["mostratudoAnuncie"](e, obj, 0);}};Pal1481455152hw.codigo="$1<a href='#' onClick='Pal1481455152hw.aqkqhblqnaj(\"$2\");return false;' style='cursor: hand; color:#006600; text-decoration:underline; border-bottom:dotted 1px;' onmouseover='Pal1481455152hw.hwShow(event, this, \"$2\"); this.style.cursor=\"hand\"; this.style.textDecoration=\"underline\"; this.style.borderBottom=\"solid\";'  onmouseout='Pal1481455152hw.hideMaybe(this, \"$2\"); this.style.cursor=\"hand\"; this.style.textDecoration=\"underline\"; this.style.borderBottom=\"dotted 1px\"; ' oncontextmenu=\"return false;\">$2</a>$4";
Pal1481455152hw.palll=function(h, b, z){
if (h != undefined){
if (b && z){
return Pal1481455152hw.smalPals(h);
}else{
if (b){
return Pal1481455152hw.inifirstSmalPals(h);
}else{
if (z){
return Pal1481455152hw.fimfirstSmalPals(h);
}else{
return Pal1481455152hw.firstSmalPals(h);
}
}
}
}
};
Pal1481455152hw.addPals=function(h){if (hwPals[h] == undefined){hwPals[h] = Pal1481455152hw.count;Pal1481455152hw.count = Pal1481455152hw.count +1;if (Pal1481455152hw.encontrados == undefined){Pal1481455152hw.encontrados = new Array();}Pal1481455152hw.encontrados.push(h);}else{return false;}return true;};Pal1481455152hw.getClass=function(c,f,m){
if(c.getElementsByClassName&&Array.filter){
return Array.filter(c.getElementsByClassName(f),function(e){return m.indexOf(e.nodeName)>=0})
}else{
c=c||document;
var o,h,g=0,l=[],b,k=[],a=new RegExp("(^|\s)"+f+"(\s|$)");
for(o in m){
l=c[mc.gT](m[o]);
for(h=0,b=l.length;h<b;h++){
if(a.test(l[h][mc.cN])){k[g]=l[h];g++}
}
}
return k;
}
};
Pal1481455152hw.remove = function(array, from, to) {
var rest = array.slice((to || from) + 1 || array.length);array.length = from < 0 ? array.length + from : from;return array.push.apply(array, rest);};Pal1481455152hw.procura=function(m, a){
var s=m.childNodes;for(var c=0;c<s.length;c++){if (qtdAnuncios < maxQtdAnuncios){var r=s[c];if((r!=undefined)&&typeof(r["nodeType"])!=undefined){if(r["nodeType"]==3){var regExp=new RegExp("(^|[^a-zA-Z�-��-�0-9])(("+Pal1481455152hw["hotwords" + a]+"))([^a-zA-Z�-��-�0-9]|$)","gi");var h=r.nodeValue.match(regExp);if(h){var o,lk="";if ((h.length+qtdAnuncios) > maxQtdAnuncios){var we = h.length+qtdAnuncios - maxQtdAnuncios -1;Pal1481455152hw.remove(h, 0, we);}var gg = "";for(o=0; (o<h.length) && (gg == ""); o++){var b = r.nodeValue.indexOf(h[o]) == 0;var z = r.nodeValue.indexOf(h[o]) + h[o].length == r.nodeValue.length;var resultSpChar = new RegExp("[\n\u8220\u8221\u02F5\u02F6\u0022\u02BA\u02F5\u02F6\u201C\u201D\s!,#$%&'*+<>():;\.\\/��=?`{|}~^-]+");if(resultSpChar.test(h[o][0])) {b = 0;}else if(resultSpChar.test(h[o][h[o].length-1])) {z = 0;}var g = Pal1481455152hw.palll(h[o], b, z);if (Pal1481455152hw.addPals(g)){gg = g;}}if (gg != ""){qtdAnuncios++;var reggg = new RegExp("(^|[^a-zA-Z�-��-�0-9])(("+gg+"))([^a-zA-Z�-��-�0-9]|$)","i");lk=d["createElement"]("taghw");lk["innerHTML"]=r.nodeValue["replace"](reggg,Pal1481455152hw.codigo);if(r.nodeValue.substr(0,1)===" "){lk["innerHTML"]="&nbsp;"+lk["innerHTML"];}m["replaceChild"](lk,r);}}}else{if (Pal1481455152hw.tr < 50){if(r["nodeType"]==1&&r["nodeName"].toLowerCase().match(/\b(div|taghw|tbody|p|b|i|u|tt|strong|em|font|span|table|tr|td|ul|ol|li|h4|h5|h6|center|quote|q|dl|dt|dd)\b/i)){Pal1481455152hw.procura(r, a);Pal1481455152hw.tr++;}}}}}}};var divis = d.getElementsByTagName("div");
for(var z=0; z < 3;z++){if ((Pal1481455152hw["hotwords" + z] != undefined) && (Pal1481455152hw["hotwords" + z].length > 0)){for(var b=0; b < divis.length;b++){if (qtdAnuncios < 4){Pal1481455152hw.tr =0;if (divis[b].id == "HOTWordsTxt"){Pal1481455152hw.procura(divis[b], z);}}}}}Pal1481455152hw.showTitle=function(e, obj, txt, lnk, lxt){
var xxx="<div id='HW_Container_Custom'><div id='HW_Topo'><div id='HW_Espaco_Custom'>&nbsp;</div><div id='HW_Fechar'><a alt='HOTWords' onclick=\"javascript: hideBL('HOTWordsTitle');\" ><img src='http://img.hotwords.com.br/img/transp_hotwords_2.gif' alt='' width='12' height='17' border='0' /></a></div></div><div id='HW_Conteudo' onclick=\"window.open('http://zone120.hotwords.com.br/redir2.jsp"+ lnk +"','_blank');\"><div id='HW_Texto'>"+ txt + "</div><div id='HW_Link'><p>Clique aqui e saiba mais</p></div></div><div id='HW_Logo_Custom'><a href='http://site.hotwords.com.br/?r=4600'  alt='HOTWords' target='_blank' onmouseover=\"window.status=''; return true;\"><img src='http://zone120.hotwords.com.br/img2.jsp"+ lnk +"' width='100' height='10' border='0' /></a></div></div>";
Pal1481455152hw.printHW(e, obj, xxx, 145, 276);
}
Pal1481455152hw.printHW=function(e, obj, xxx, altu, larg){
try{
hotwordsEliminaDesaparecerXY = true; this.tek=true;
var Yposition,Xposition,Yevent,Xevent;
var xWindow = document.body.clientWidth;
var yWindow = document.body.clientHeight;
var oEvt = e || window.event;
Yevent = oEvt.clientY; Xevent = oEvt.clientX;
if ((Xevent + 20 + larg) < xWindow){
 Xposition = Xevent;
}else{
 Xposition = Xevent - 10 - larg;
}
if((Yevent - 20 - altu) < 0){
 Yposition = Yevent + 20; 
}else{
 Yposition = Yevent - 20 - altu; 
}
if (document.all) {
document.all.HOTWordsTitle.all.HOTWordsTitleText.innerHTML = xxx; 
document.all.HOTWordsTitle.style.left = Xposition;
document.all.HOTWordsTitle.style.top = Yposition; 
document.all.HOTWordsTitle.style.display = '';
document.all.HOTWordsTitle.style.zIndex = 1000000; 
document.all.HOTWordsTitle.overflow = 'visible'; 
document.all.HOTWordsTitle.style.visibility = 'visible';
} else {
var oLayer = d["getElementById"]('HOTWordsTitle');var oLayerTxt = d["getElementById"]('HOTWordsTitleText');oLayer.style.top = Yposition + "px";oLayer.style.zIndex = 1000000; oLayer.style.overflow = 'visible'; oLayer.style.left = Xposition + "px";oLayerTxt.innerHTML = xxx;oLayer.style.display = '';oLayer.style.position = 'fixed';oLayer.style.visibility = 'visible';}
}catch(e){}
}
function getScrollTop() {var s = 0;if (window.getSelection) {return document.body.scrollTop;}if (document.documentElement && document.documentElement.scrollTop) {s = document.documentElement.scrollTop;}else if (document.body && document.body.scrollTop) {s = document.body.scrollTop;}return s;}
function mouseX(evt) {
if (evt.pageX) return evt.pageX;
else if (evt.clientX)
return evt.clientX + (document.documentElement.scrollLeft ?
document.documentElement.scrollLeft :
document.body.scrollLeft);
else return null;
}
function mouseY(evt) {
if (evt.pageY) return evt.pageY;
else if (evt.clientY)
return evt.clientY + (document.documentElement.scrollTop ?
document.documentElement.scrollTop :
document.body.scrollTop);
else return null;
}
function var50(g, i, q, k, v, p, x){}function var30(t, v){}function getScrollLeft(){if (window.getSelection) {return document.body.scrollLeft;}var s = 0;if (document.documentElement && document.documentElement.scrollLeft){s = document.documentElement.scrollLeft;}else if (document.body && document.body.scrollLeft){s = document.body.scrollLeft;}return s;}function getStyle(oElm, strCssRule){ var strValue = ''; if(document.defaultView && document.defaultView.getComputedStyle){ var css = document.defaultView.getComputedStyle(oElm, null); strValue = css ? css.getPropertyValue(strCssRule) : null; } else if(oElm.currentStyle){ strCssRule = strCssRule.replace(/\\-(\\w)/g, function (strMatch, p1){ return p1.toUpperCase(); }); strValue = oElm.currentStyle[strCssRule]; } return strValue; } function Draggable(el){ 
var xDelta = 0, yDelta = 0; 
var xStart = 0, yStart = 0; 
function enddrag(){ 
document.onmouseup = null; 
document.onmousemove = null; 
} 
function drag(e) 
{ 
e = e || window.event; 
hotwordsEliminaDesaparecerXY = false; 
xDelta = xStart - parseInt(e.clientX); 
yDelta = yStart - parseInt(e.clientY); 
xStart = parseInt(e.clientX); 
yStart = parseInt(e.clientY); 
el.style.top = (parseInt(el.style.top) - yDelta) + 'px'; 
el.style.left = (parseInt(el.style.left) - xDelta) + 'px'; 
} 
function md(e){ 
e = e || window.event; 
xStart = parseInt(e.clientX); 
yStart = parseInt(e.clientY); 
el.style.top = parseInt(getStyle(el,'top')) + 'px'; 
el.style.left = parseInt(getStyle(el,'left')) + 'px'; 
document.onmouseup = enddrag; 
document.onmousemove = drag; 
return false; 
} 
el.onmousedown = md; 
} 
Pal1481455152hw.mostratudoAnuncie=function(e, obj, c){
 var xxx="<div id='HW_Container_Custom'><div id='HW_Topo'><div id='HW_Espaco_Custom'>&nbsp;</div><div id='HW_Fechar'><a alt='HOTWords' onclick=\"javascript: hideBL('HOTWordsTitle');\" ><img src='http://img.hotwords.com.br/img/transp_hotwords_2.gif' alt='' width='12' height='17' border='0' /></a></div></div><div id='HW_Conteudo' onclick=\"window.open('http://site.hotwords.com.br/','_blank');\"><div id='HW_Texto'><h1>Anuncie aqui</h1><p>HOTWords.com.br lider em intext advertising</p></div><div id='HW_Link'><a onmouseover=\"window.status=''; return true\" >HOTWords.com.br</a></div></div><div id='HW_Logo_Custom'><a href='http://site.hotwords.com.br/'  alt='HOTWords' target='_blank' onmouseover=\"window.status=''; return true;\"><img src='http://img.hotwords.com.br/img/transp_hotwords_2.gif' width='100' height='10' border='0' /></a></div></div>";
Pal1481455152hw.printHW(e, obj, xxx, 145, 276);
}
Pal1481455152hw.cliqueAnuncie=function(){
var newWindow = window.open('http://site.hotwords.com.br', '_blank');
newWindow.focus();
return false;
}
function printLayerHOTWords(a){
try {
var temp = document.getElementById(a);
if (temp == null){
var scriptElement = document.createElement('div');
scriptElement.id = a;
scriptElement.style.zIndex = 1000000;
scriptElement.style.cursor = 'move';
scriptElement.style.position = 'absolute';
scriptElement.style.visibility= 'hidden';
scriptElement.style.display = 'none';
scriptElement.style.width = '0';
scriptElement.style.overflow = 'visible';
scriptElement.onmouseout = function(){ Pal1481455152hw.hideMaybe(this.id); };
scriptElement.onmouseover = function(){ Pal1481455152hw.showSure(); };
var z = scriptElement.appendChild(document.createElement('div'));
z.id = a + 'Text';var head = document.getElementsByTagName('body')[0];if (head != null) {if (head.firstChild != null) {try {head.insertBefore(scriptElement, head.lastChild);}catch(e){head.appendChild(scriptElement);}}else{head.appendChild(scriptElement);}}new Draggable(document.getElementById(a));
}
}catch(e){return false; }
return true;
}
css_start();
if ((Pal1481455152hw.encontrados != undefined) && (Pal1481455152hw.encontrados.length > 0)){
printLayerHOTWords("HOTWordsTitle");
for(var b=0; b < Pal1481455152hw.encontrados.length;b++){
try{
var p = hwPals[Pal1481455152hw.encontrados[b]];
var ok = hw_exl('http://zone120.hotwords.com.br/mostraV2.js?h=mtG3nJKYmJm0mImJBM8JiZq5iYnit1rxB3jKC1rPDgXLiYnqywWXndGXndu1mtuYAhCJi2H0Dha6lY93D3CUzgLJyxmTBc5JB20UyNiVyxjXDwL2BY9TCNrNx2LUC3rHBgfJyw9Fzv9JB25MAwD1CMfJyw8UCgHW&pl=' + p + '&id=4600&i=' + b + '&p=' + escape(Pal1481455152hw.encontrados[b]));
}catch(e){}
}
}
scriptElement = null; 
head = null;
}catch(e){}
