(ns programming-clojure.examples
	(:require [clojure.string :as str])
	(:use midje.sweet))

(fact "list destructuring"
	(let [[x y & rest :as original-arg] (range 5)]
		x => 0
		y => 1
		rest => [2 3 4]
		original-arg => (range 5)))

(fact "nested destructuring"
			(let [[x [y z] & rest ] [1 [2 3] 4 5]]
				x => 1
				y => 2
				z => 3
				rest => [4 5]))

(def test-map {:a 1 :b 2 :c 3})

(fact "map destructuring with defaults"
	(let [{b :b, d :d :as args :or {b 12 d 4}} test-map]
		b => 2
		d => 4
		args => test-map))

(fact "map destructuring with key bindings"
	(let [{:keys [a b c d]} test-map]
		b => 2
		d => nil))

(def user-info ["robert8990" 2011 :name "Bob" :city "Boston"])

(fact "destructuring with rest as map"
			(let [[username account-year & {:keys [name city]}] user-info]
				account-year => 2011
				name => "Bob"
				city => "Boston"))


; destructuring used in function args list can simulate null-arity
(defn make-user [& [user-id]]
	{:user-id (or user-id
								(str (java.util.UUID/randomUUID)))})

(fact "destructuring used in function args list can simulate null-arity"
			(make-user) => (just {:user-id string?} )
			(make-user "123") => {:user-id "123"})


(defn make-user2
	[username & {:keys [email join-date]
							 :or   {join-date (java.util.Date.)}}]
	{:username username
	 :join-date join-date
	 :email email})

(fact "keyword argument example - using destructuring"
			(make-user2 "Bruce Lee") => (contains {:username "Bruce Lee"})
			(make-user2 "Chuck Norris" :email "chuck@norris.com")
				=> (contains {:username "Chuck Norris" :email "chuck@norris.com"}))




