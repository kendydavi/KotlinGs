# 🌱 AgroSat Sentinel

**Monitoramento Agrícola via Dados Satelitais**

> Global Solution 2026.1 — FIAP · 3º Ano Sistemas de Informação · Turmas de Agosto

---

## 📋 Descrição da Solução

O **AgroSat Sentinel** é um aplicativo Android de monitoramento agrícola que democratiza o acesso a dados satelitais para pequenos e médios produtores rurais brasileiros. A solução transforma índices de vegetação (NDVI) obtidos via satélite em alertas simples e acionáveis, permitindo que o produtor tome decisões de manejo antes que problemas se agravem.

O produtor cadastra seus talhões (áreas de lavoura), visualiza o histórico de saúde da vegetação pelo índice NDVI, acompanha a previsão do tempo em tempo real e recebe alertas automáticos sobre riscos climáticos como geada, seca, chuva intensa e vento forte.

**Diferenciais:**
- Dados satelitais do Sentinel-2 (ESA) — zero custo de aquisição de imagens
- Interface acessível, sem necessidade de conhecimento técnico em sensoriamento remoto
- Alertas proativos por tipo de risco climático e por talhão
- Funciona com fallback offline quando sem conexão

---

## 🚀 Tema da Global Solution — Space Connect

O tema da Global Solution 2026.1 é **Space Connect: Tecnologia Espacial Aplicada a Desafios Reais**, proposto pela FIAP em parceria com a indústria espacial.

O desafio convida os alunos a criarem soluções inovadoras que conectem o ecossistema espacial aos problemas reais da sociedade, utilizando tecnologias emergentes e dados em escala global para gerar impacto positivo.

O **AgroSat Sentinel** responde diretamente à sugestão de **monitoramento agrícola com dados de satélite para aumento de produtividade e redução de perdas**, alinhando-se aos seguintes ODS da ONU:

| ODS | Objetivo |
|-----|----------|
| 🌾 ODS 2 | Fome zero e agricultura sustentável |
| 🏭 ODS 9 | Indústria, inovação e infraestrutura |
| 🌡️ ODS 13 | Ação contra a mudança global do clima |
| 💼 ODS 8 | Trabalho decente e crescimento econômico |

---

## 🗺️ Fluxo de Telas

```
┌─────────────────┐
│   SplashScreen  │ ← Exibe logo, verifica SharedPreferences (2 segundos)
└────────┬────────┘
         │
    ┌────┴────┐
    │         │
    ▼         ▼
┌───────┐  ┌──────────────────┐
│ Home  │  │ OnboardingScreen │ ← Apenas na primeira execução
│Screen │  │  (3 páginas)     │
└───┬───┘  └────────┬─────────┘
    ▲               │ Salva SharedPreferences → navega para Home
    └───────────────┘
         │
         ▼
┌─────────────────────────────────────────┐
│              HomeScreen                 │
│  Dashboard com previsão do tempo (API)  │
│  Botões de navegação rápida             │
└─────────┬───────────────┬───────────────┘
          │               │               │
          ▼               ▼               ▼
┌──────────────┐  ┌──────────────┐  ┌──────────────┐
│ FieldList    │  │  Monitoring  │  │    Alerts    │
│ Screen       │  │  Screen      │  │    Screen    │
│ (Talhões)    │  │  (NDVI)      │  │  (Alertas)   │
└──────┬───────┘  └──────────────┘  └──────────────┘
       │
       ▼
┌──────────────────┐
│ FieldDetail      │
│ Screen           │
│ (Detalhe talhão  │
│  + NDVI + Clima) │
└──────────────────┘
```

### Descrição de cada tela

**SplashScreen** — Tela inicial com logo e nome do app. Verifica via `SharedPreferences` se o usuário já viu o onboarding. Transição automática após 2 segundos.

**OnboardingScreen** — Apresentação do app em 3 páginas animadas com ícone, título e descrição. Botões "Próximo", "Voltar" e "Começar". Ao finalizar, salva flag no `SharedPreferences` para nunca mais exibir.

**HomeScreen** — Dashboard principal com previsão do tempo das próximas horas (via API Open-Meteo), botão de atualização e navegação rápida para Talhões, NDVI e Alertas. Trata os 4 estados: Idle, Loading, Success e Error.

**FieldListScreen** — Lista de talhões cadastrados em grid com campo de busca/filtro por nome ou cultura. Cada talhão tem botão de favoritar com toggle visual de estrela.

**FieldDetailScreen** — Detalhe do talhão selecionado com informações cadastrais, histórico de leituras NDVI com badge colorido (verde/amarelo/vermelho) e previsão do tempo específica para a localização do talhão.

**MonitoringScreen** — Histórico NDVI com chips de filtro por talhão, card de resumo (último valor + média do período) e lista completa de leituras com status visual.

**AlertsScreen** — Lista de alertas climáticos com diferenciação visual entre lidos e não lidos. Botão individual "Marcar como lido" e ação "Marcar todos como lidos". Ícone muda conforme tipo de alerta (chuva, seca, geada, vento, NDVI).

---

## 📱 Prints das Telas

> **Instruções:** Adicione os prints abaixo após capturar as telas no emulador ou dispositivo físico.  
> No Android Studio: **File → Take Screenshot** ou use o ícone de câmera na barra lateral do emulador.

| Tela | Print |
|------|-------|
| Splash | <img width="350" height="654" alt="image" src="https://github.com/user-attachments/assets/a83fba5e-78a8-4b19-921a-a68c2b6b8bf2" /> |
| Onboarding | <img width="374" height="658" alt="image" src="https://github.com/user-attachments/assets/f4390436-9e52-4572-8a67-286c67134628" />
 <img width="370" height="666" alt="image" src="https://github.com/user-attachments/assets/d25f750e-1c4c-4170-b32b-7992b7584d77" />
 <img width="369" height="660" alt="image" src="https://github.com/user-attachments/assets/4e190113-2df4-4b63-bb80-e8a49ce2f0c1" /> |
| Home | <img width="378" height="670" alt="image" src="https://github.com/user-attachments/assets/483fa881-4fdc-44ed-bdc8-7e44fdb252de" /> |
| Talhões | <img width="376" height="666" alt="image" src="https://github.com/user-attachments/assets/9c35c6a6-2e78-4760-b06c-be753097a94d" /> |
| Detalhe Talhão | <img width="370" height="674" alt="image" src="https://github.com/user-attachments/assets/a22d63e6-ec05-4264-8309-7b3befb2c233" /> |
| Monitoramento NDVI | <img width="370" height="668" alt="image" src="https://github.com/user-attachments/assets/59c92178-389b-4c84-ac05-48a31d153afd" /> |
| Alertas | <img width="344" height="650" alt="image" src="https://github.com/user-attachments/assets/da1ffaac-f0b3-494f-9497-ee3ed551be6a" /> |

---

## 🌐 API Utilizada

**Open-Meteo Weather API**
- **URL Base:** `https://api.open-meteo.com/v1/`
- **Endpoint:** `GET /forecast`
- **Documentação:** https://open-meteo.com/en/docs
- **Autenticação:** Nenhuma (API pública e gratuita)
- **Dados retornados:** Temperatura, probabilidade de precipitação e velocidade do vento por hora

**Exemplo de requisição:**
```
GET https://api.open-meteo.com/v1/forecast
  ?latitude=-23.5505
  &longitude=-46.6333
  &hourly=temperature_2m,precipitation_probability,windspeed_10m
  &forecast_days=3
  &timezone=America/Sao_Paulo
```

**Dados NDVI:** Gerados via mock local simulando leituras do satélite Sentinel-2 (ESA). Em produção, seriam obtidos via API Copernicus Data Space com as bandas B04 e B08 e cálculo `NDVI = (B08 - B04) / (B08 + B04)`.

---
