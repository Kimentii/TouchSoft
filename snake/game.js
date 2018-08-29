const HEIGHT_IN_BLOCKS = 20;
const WIDTH_IN_BLOCKS = 20;
const BLOCK_WIDTH = 30;
const BLOCK_HEIGHT = 30;

const RIGHT = 1;
const LEFT = 2;
const UP = 3;
const DOWN = 4;

const KEY_CODE_ARROW_RIGHT = 39;
const KEY_CODE_ARROW_LEFT = 37;
const KEY_CODE_ARROW_UP = 38;
const KEY_CODE_ARROW_DOWN = 40;
const REFRESH_PERIOD_MS = 130;

const COLOR_SNAKE = "#000000";
const COLOR_FREE_CELL = "#FFFFFF";
const COLOR_APPLE = "#33CC33";

const INITIAL_SNAKE_SIZE = 3;

var snake = [];
var directions = [RIGHT];
var oldTail = [];
var apple = [];
var score = 0;


var body = document.getElementById("body");
for (var i = 0; i < WIDTH_IN_BLOCKS; i++) {
    for (var j = 0; j < HEIGHT_IN_BLOCKS; j++) {
        var block = document.createElement("div");
        block.style.position = "absolute";
        block.style.width = BLOCK_WIDTH + "px";
        block.style.height = BLOCK_HEIGHT + "px";
        block.style.left = i * BLOCK_WIDTH + "px";
        block.style.top = j * BLOCK_HEIGHT + "px";
        body.appendChild(block);
    }
}
var field = body.getElementsByTagName("div");

window.setInterval(function () {
    var moveDirection = getMoveDirection();
    moveSnake(moveDirection);
    drawSnake();
    showSnake();
}, REFRESH_PERIOD_MS);

window.onkeydown = function (e) {
    var key = e.keyCode;
    switch (key) {
        case KEY_CODE_ARROW_RIGHT:
            if (directions[directions.length - 1] !== LEFT) {
                directions.push(RIGHT);
                //direction = RIGHT;
            }
            break;
        case
        KEY_CODE_ARROW_LEFT:
            if (directions[directions.length - 1] !== RIGHT) {
                directions.push(LEFT);
                //direction = LEFT;
            }
            break;
        case
        KEY_CODE_ARROW_UP:
            if (directions[directions.length - 1] !== DOWN) {
                directions.push(UP);
                // direction = UP;
            }
            break;
        case
        KEY_CODE_ARROW_DOWN:
            if (directions[directions.length - 1] !== UP) {
                directions.push(DOWN);
                //direction = DOWN;
            }
            break;
    }
}

generateSnake();
drawSnake();
generateNewApple();
drawApple();

function showSnake() {
    // var str;
    // snake.forEach(function (e) {
    //     str += ("[" + e[0] + ", " + e[1] + "]");
    // })
    // console.log(str);
}

function getMoveDirection() {
    if (directions.length == 1) {
        return directions[0];
    } else {
        return directions.shift();
    }
}

function moveSnake(direction) {
    oldTail[0] = snake[snake.length - 1][0];
    oldTail[1] = snake[snake.length - 1][1];
    for (var i = snake.length - 1; i > 0; i--) {
        snake[i][0] = snake[i - 1][0];
        snake[i][1] = snake[i - 1][1];
    }
    var snakeNewHead = [];
    snakeNewHead[0] = snake[0][0];
    snakeNewHead[1] = snake[0][1];
    switch (direction) {
        case RIGHT:
            snakeNewHead[0] += 1;
            break;
        case LEFT:
            snakeNewHead[0] -= 1;
            break;
        case UP:
            snakeNewHead[1] -= 1;
            break;
        case DOWN:
            snakeNewHead[1] += 1;
            break;
    }
    if (snake.find(function (el) {
        return (el[0] == snakeNewHead[0] && el[1] == snakeNewHead[1]);
    }) !== undefined) {
        showGameOver();
    } else {
        snake[0][0] = snakeNewHead[0];
        snake[0][1] = snakeNewHead[1];
    }
    if ((snakeNewHead[0] >= WIDTH_IN_BLOCKS) || (snakeNewHead[1] >= HEIGHT_IN_BLOCKS) ||
        (snakeNewHead[0] < 0) || (snakeNewHead[1] < 0)) {
        showGameOver();
    }
    if ((snake[0][0] == apple[0]) && (snake[0][1] == apple[1])) {
        snake.push(oldTail);
        oldTail = [];
        generateNewApple();
        drawApple();
        score++;
    }
}

function generateSnake() {
    const initialX = rand(INITIAL_SNAKE_SIZE, WIDTH_IN_BLOCKS - INITIAL_SNAKE_SIZE * 2);
    const initialY = rand(0, HEIGHT_IN_BLOCKS);
    for (var i = 0; i < INITIAL_SNAKE_SIZE; i++) {
        snake.push([initialX - i, initialY]);
    }
    // showSnake()
}

function generateNewApple() {
    do {
        apple[0] = rand(0, HEIGHT_IN_BLOCKS);
        apple[1] = rand(0, WIDTH_IN_BLOCKS);
    } while (snake.find(function (element) {
        return (element[0] === apple[0] && element[1] === apple[1]);
    }) != undefined);
}

function drawSnake() {
    if (oldTail != null && oldTail.length > 0) {
        fillCellWithColor(oldTail[0], oldTail[1], COLOR_FREE_CELL);
    }
    for (var i = 0; i < snake.length; i++) {
        fillCellWithColor(snake[i][0], snake[i][1], COLOR_SNAKE);
    }
}

function drawApple() {
    fillCellWithColor(apple[0], apple[1], COLOR_APPLE);
}

function showGameOver() {
    window.location.href = "game_over.html";
    localStorage.setItem("score", score);
}

function fillCellWithColor(x, y, color) {
    field[x * WIDTH_IN_BLOCKS + y].style.backgroundColor = color;
    // field[x * WIDTH_IN_BLOCKS + y].style.border = "1px solid #000000"
}

function rand(min, max) {
    k = Math.floor(Math.random() * (max - min) + min);
    return k;
}