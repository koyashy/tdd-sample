<?php
require_once __DIR__ . '/Calculator.php';
require_once __DIR__ . '/View.php';

$a = $_GET['a'];
$b = $_GET['b'];

$calculator = new Calculator();
$result = $calculator->add($a, $b);

$c = new View(__FILE__);
$c->render(compact('result'));