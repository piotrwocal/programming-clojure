(ns programming-clojure.midje-usage
	(:require [clojure.string :as str])
	(:use midje.sweet))

(fact "lets start with sth simple"
			(+ 2 2) => 4
			(+ 2 2) => even?)

(fact "`split` splits strings on regular expressions and returns a vector"
			(str/split "a/b/c" #"/") => ["a" "b" "c"]
			(str/split "" #"irrelevant") => [""]
			(str/split "no regexp matches" #"a+\s+[ab]") => ["no regexp matches"])

(tabular
	(fact (+ ?x ?y) => ?expected)
	?x ?y ?expected
	0 0 0
	1 2 3
	5 2 7)

(fact
	[1 2 3] => (just [odd? even? odd?]))


; top-down testing
(unfinished pilot-ready copilot-ready flight-engineer-ready)

(defn ready []
	(and (pilot-ready) (copilot-ready) (flight-engineer-ready)))

(facts "Using against-background after"
			 (ready) => true

			 (ready) => false (provided (pilot-ready) => false)
			 (ready) => false (provided (copilot-ready) => false)
			 (ready) => false (provided (flight-engineer-ready) => false)

			 (against-background
				 (pilot-ready) => true, (copilot-ready) => true, (flight-engineer-ready) => true))


(against-background [(pilot-ready) => true, (copilot-ready) => true, (flight-engineer-ready) => true]
										(fact "or before facts"
													(ready) => true))

;top-down testing with mocked methods with params
(unfinished my-odd)

(defn my-odd-check[x]
	(my-odd x))

(fact
	(my-odd-check 7) => 8 (provided (my-odd 7) => 8))









