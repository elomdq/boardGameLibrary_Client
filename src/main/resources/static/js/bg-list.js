window.addEventListener('load',loadGames);

const gamesContainer = document.querySelector('.games-container');

let atroposInstances = [];
let gamesList = [];
let gameObj = {
    imgURL:''
}

// The username and password
// DO NOT store credentials in your JS file like this
let username = 'user';
let password = 'password';

async function loadGames(){
    try {
        await callGamesApi();

    } catch (error) {
        console.log(error);
    }
    insertGamesToHTML(gamesList);
}

function code(username, password){
    return btoa(`${username}:${password}`);
}

async function callGamesApi(){
    const url = 'http://localhost:8080/api/library/boardgames/list';
    const auth;

    try {
        auth = await code(username, password);
        console.log(auth)
        const response = await fetch(url,{
            headers: {'Authorization': 'Basic '+auth}}
            );
        const games = await response.json();
        gamesList = games.map(game => game = {...game,...gameObj});
    } catch (error) {
        console.log(error);
    }
}

async function insertGamesToHTML(games = []){

    for(const game of games){
        const atroposScale = document.createElement('DIV');
        atroposScale.classList.add('atropos-scale');

        const atroposRotate = document.createElement('DIV');
        atroposRotate.classList.add('atropos-rotate');

        const atroposInner = document.createElement('DIV');
        atroposInner.classList.add('atropos-inner');

        const gameContainer = document.createElement('DIV');
        gameContainer.classList.add('game-container', 'atropos', 'atropos-box');

        atroposRotate.appendChild(atroposInner);
        atroposScale.appendChild(atroposRotate);
        gameContainer.appendChild(atroposScale);

        const title = document.createElement('DIV');
        title.className = 'title-container d-flex flex-row flex-wrap justify-content-between align-items-start mb-2';
        title.innerHTML=`
            <p class="game-title">${game.name}</p>
            <p class="year">${game.bgYear}</p>
        `;
        title.dataset.atroposOffset = 2;

        const img = document.createElement('DIV');
        img.classList.add('img-container');

        try {
            const response = await fetch(`http://localhost:8080/api/library/images/list/${game.id}`,{headers: {'Authorization': 'Basic '+auth}});
            const imgs = await response.json();
            if(imgs[0]){
                //url =  imgs[0].url.concat('/'+imgs[0].name);
                let url = imgs[0].game.id + '/' +imgs[0].name;
                game.imgURL =  'http://localhost:8082/'+ url;
            }
        } catch (error) {
            console.log(error);
        }

        if(game.imgURL===''){
            img.innerHTML = `
                <a class="game-link" href="#">
                    <img src="../assets/img/pic3043734.jpg" alt="${game.name} front image" />
                </a>
            `;
        }else{
            img.innerHTML = `
                <a class="game-link" href="#">
                    <img src="${game.imgURL}" alt="${game.name} front image" />
                </a>
            `;
        }

        img.dataset.atroposOffset=4;

        atroposInner.appendChild(title);
        atroposInner.appendChild(img);
        gamesContainer.appendChild(gameContainer);
    }

    crearInstanciasAtropos();
}

function crearInstanciasAtropos(){
    document.querySelectorAll('.atropos-box').forEach(element=>{
        atroposInstances = [...atroposInstances, Atropos({
            el: element,
            activeOffset: 40,
            shadow: false,
            onEnter() {
                //console.log('Enter');
            },
            onLeave() {
                //console.log('Leave');
            },
            onRotate(x, y) {
                //console.log('Rotate', x, y);
            }
        })];
    });
}

