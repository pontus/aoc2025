
(require '[clojure.repl :refer :all])

(defn get-input [] (let [r (clojure.java.io/reader "input.txt")] r))


(defn charnum [a] (- a 48))


(defn index
  [element list]
  (printf "Finding %s in %s : %d\n\n"
          element
          list
          (.indexOf (seq list) element))
  (.indexOf (seq list) element))

(defn vectorwithout
  [id v]
  (if (<= id 0)
    (subvec v 1)
    (if (< id (count v)) (concat (subvec v 0 id) (subvec v (inc id))))))

(defn linetonums [l] (vec (map charnum (map int (char-array l)))))

(defn highestinvector
  [v]
  (let [highest (apply max v)
        highestpos (index highest v)
        remaining (vectorwithout highestpos v)]
    (vector highest highestpos remaining)))

(defn maxline
  [l]
  (let [nums (linetonums l)
        lastindex (dec (count nums))
        hiv (highestinvector nums)
        resp (if (= lastindex (get hiv 1))
               (+ (* 10 (get (highestinvector (vec (get hiv 2))) 0))
                  (get hiv 0))
               (+ (* 10 (get hiv 0))
                  (get (highestinvector (subvec (vec (get hiv 2)) (get hiv 1)))
                       0)))]
    (printf "Line %s: %d\n" l resp)
    resp))

(defn maxtotal
  [input]
  (let [linemaxes (map maxline input)] (apply + linemaxes)))

(printf "%d\n\n" (+ 2 3))
(printf "%s\n\n" (get-input))
(printf "%d\n\n" (maxtotal (line-seq (get-input))))

