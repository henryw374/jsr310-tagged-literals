(ns jsr310-tagged-literals.read-write
  #?@
  (:cljs
    [(:require
       [java.time :refer [Period
                          LocalDate
                          LocalDateTime
                          ZonedDateTime
                          OffsetTime
                          Instant
                          OffsetDateTime
                          ZoneId
                          DayOfWeek
                          LocalTime
                          Month
                          Duration
                          Year
                          YearMonth]]
       [cljs.reader :as reader])]
    :clj
    [(:import (java.io Writer)
       [java.time Period
                  LocalDate
                  LocalDateTime
                  ZonedDateTime
                  OffsetTime
                  Instant
                  OffsetDateTime
                  ZoneId
                  DayOfWeek
                  LocalTime
                  Month
                  Duration
                  Year
                  YearMonth])]))

(defn- print-to-string [t o]
  (str "#jsr310/" t " \"" (str o) "\""))

(def print-period (partial print-to-string "period"))
(def print-date (partial print-to-string "date"))
(def print-date-time (partial print-to-string "date-time"))
(def print-zoned-date-time (partial print-to-string "zoned-date-time"))
(def print-offset-time (partial print-to-string "offset-time"))
(def print-instant (partial print-to-string "instant"))
(def print-offset-date-time (partial print-to-string "offset-date-time"))
(def print-zone (partial print-to-string "zone"))
(def print-day-of-week (partial print-to-string "day-of-week"))
(def print-time (partial print-to-string "time"))
(def print-month (partial print-to-string "month"))
(def print-duration (partial print-to-string "duration"))
(def print-year (partial print-to-string "year"))
(def print-year-month (partial print-to-string "year-month"))

#?(:cljs
   (extend-protocol IPrintWithWriter
     Period      (-pr-writer [d writer opts]        (-write writer (print-period d)))
     LocalDate      (-pr-writer [d writer opts]        (-write writer (print-date d)))
     LocalDateTime      (-pr-writer [d writer opts]        (-write writer (print-date-time d)))
     ZonedDateTime      (-pr-writer [d writer opts]        (-write writer (print-zoned-date-time d)))
     OffsetTime      (-pr-writer [d writer opts]        (-write writer (print-offset-time d)))
     Instant      (-pr-writer [d writer opts]        (-write writer (print-instant d)))
     ;OffsetDateTime      (-pr-writer [d writer opts]        (-write writer (print-offset-date-time d)))
     ZoneId      (-pr-writer [d writer opts]        (-write writer (print-zone d)))
     DayOfWeek      (-pr-writer [d writer opts]        (-write writer (print-day-of-week d)))
     LocalTime      (-pr-writer [d writer opts]        (-write writer (print-time d)))
     Month      (-pr-writer [d writer opts]        (-write writer (print-month d)))
     Duration      (-pr-writer [d writer opts]        (-write writer (print-duration d)))
     Year      (-pr-writer [d writer opts]        (-write writer (print-year d)))
     YearMonth      (-pr-writer [d writer opts]        (-write writer (print-year-month d)))
     ))

#?(:clj    (defmethod print-method Period [c ^Writer w]      (.write w (print-period c))))
#?(:clj    (defmethod print-method LocalDate [c ^Writer w]      (.write w (print-date c))))
#?(:clj    (defmethod print-method LocalDateTime [c ^Writer w]      (.write w (print-date-time c))))
#?(:clj    (defmethod print-method ZonedDateTime [c ^Writer w]      (.write w (print-zoned-date-time c))))
#?(:clj    (defmethod print-method OffsetTime [c ^Writer w]      (.write w (print-offset-time c))))
#?(:clj    (defmethod print-method Instant [c ^Writer w]      (.write w (print-instant c))))
#?(:clj    (defmethod print-method OffsetDateTime [c ^Writer w]      (.write w (print-offset-date-time c))))
#?(:clj    (defmethod print-method ZoneId [c ^Writer w]      (.write w (print-zone c))))
#?(:clj    (defmethod print-method DayOfWeek [c ^Writer w]      (.write w (print-day-of-week c))))
#?(:clj    (defmethod print-method LocalTime [c ^Writer w]      (.write w (print-time c))))
#?(:clj    (defmethod print-method Month [c ^Writer w]      (.write w (print-month c))))
#?(:clj    (defmethod print-method Duration [c ^Writer w]      (.write w (print-duration c))))
#?(:clj    (defmethod print-method Year [c ^Writer w]      (.write w (print-year c))))
#?(:clj    (defmethod print-method YearMonth [c ^Writer w]      (.write w (print-year-month c))))

#?(:clj    (defmethod print-dup Period [c ^Writer w]      (.write w (print-period c))))
#?(:clj    (defmethod print-dup LocalDate [c ^Writer w]      (.write w (print-date c))))
#?(:clj    (defmethod print-dup LocalDateTime [c ^Writer w]      (.write w (print-date-time c))))
#?(:clj    (defmethod print-dup ZonedDateTime [c ^Writer w]      (.write w (print-zoned-date-time c))))
#?(:clj    (defmethod print-dup OffsetTime [c ^Writer w]      (.write w (print-offset-time c))))
#?(:clj    (defmethod print-dup Instant [c ^Writer w]      (.write w (print-instant c))))
#?(:clj    (defmethod print-dup OffsetDateTime [c ^Writer w]      (.write w (print-offset-date-time c))))
#?(:clj    (defmethod print-dup ZoneId [c ^Writer w]      (.write w (print-zone c))))
#?(:clj    (defmethod print-dup DayOfWeek [c ^Writer w]      (.write w (print-day-of-week c))))
#?(:clj    (defmethod print-dup LocalTime [c ^Writer w]      (.write w (print-time c))))
#?(:clj    (defmethod print-dup Month [c ^Writer w]      (.write w (print-month c))))
#?(:clj    (defmethod print-dup Duration [c ^Writer w]      (.write w (print-duration c))))
#?(:clj    (defmethod print-dup Year [c ^Writer w]      (.write w (print-year c))))
#?(:clj    (defmethod print-dup YearMonth [c ^Writer w]      (.write w (print-year-month c))))

(def tags {'jsr310/period (fn [t] (. Period parse t))
           'jsr310/date (fn [t] (. LocalDate parse t))
           'jsr310/date-time (fn [t] (. LocalDateTime parse t))
           'jsr310/zoned-date-time (fn [t] (. ZonedDateTime parse t))
           'jsr310/offset-time (fn [t] (. OffsetTime parse t))
           'jsr310/instant (fn [t] (. Instant parse t))
           'jsr310/offset-date-time (fn [t] (. OffsetDateTime parse t))
           'jsr310/time (fn [t] (. LocalTime parse t))
           'jsr310/duration (fn [t] (. Duration parse t))
           'jsr310/year (fn [t] (. Year parse t))
           'jsr310/year-month (fn [t] (. YearMonth parse t))
           'jsr310/zone (fn [t] (. ZoneId of t))
           'jsr310/day-of-week (fn [t] (. DayOfWeek valueOf t))
           'jsr310/month (fn [t] (. Month valueOf t))
           })

#?(:cljs
   (do
     (doseq [[tag read-fn] tags]
       (reader/register-tag-parser! tag read-fn))))


