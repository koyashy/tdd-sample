<?php
/**
 * Created by PhpStorm.
 * User: koyasu
 * Date: 2014/01/04
 * Time: 17:06
 */

require_once __DIR__ . '/../../public/Calculator.php';

class CalculatorTest extends PHPUnit_Framework_TestCase {

    function testAdd() {
        $a = 12;
        $b = 34;

        $c = new Calculator();
        $r = $c->add($a, $b);

        self::assertEquals($a + $b, $r);
    }
}
 