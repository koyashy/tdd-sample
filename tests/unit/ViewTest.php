<?php
/**
 * Created by PhpStorm.
 * User: koyasu
 * Date: 2014/01/02
 * Time: 18:33
 */

require_once __DIR__ . '/../../public/View.php';

class ViewTest extends PHPUnit_Framework_TestCase
{
    function testSimpleRender()
    {
        ob_start();

        $view = new View(__FILE__);
        $view->render();
        $output = ob_get_contents();

        self::assertEquals('This is a test html: ${var}', $output);

        ob_end_clean();
    }

    function testRenderWithVariable()
    {
        ob_start();

        $view = new View(__FILE__);
        $view->render(
            array(
                'var' => 'sample variable'
            )
        );

        $output = ob_get_contents();

        self::assertEquals('This is a test html: sample variable', $output);

        ob_end_clean();
    }
}
 