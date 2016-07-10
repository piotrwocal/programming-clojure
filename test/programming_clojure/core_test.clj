(ns programming-clojure.core-test
	(:require [clojure.test :refer :all]
						[programming-clojure.core :refer :all])
	(:use midje.sweet))

(fact
	(+ 2 2) => 4)