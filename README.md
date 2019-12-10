# EECS_891_Project

A Comparison of Global and Saturated Probabilistic Approximations Using Characteristic Sets in Mining Incomplete Data.

# Abstract

Data mining is an important part of the knowledge discovery process. Data mining helps in finding out patterns across large data sets and establishing a relationship through data analysis to solve problems.

Input data sets are often incomplete, i.e., some attribute values are missing. The rough set theory offers mathematical tools to discover patterns hidden in inconsistent and incomplete data. Rough set theory handles inconsistent data by introducing probabilistic approximations. These approximations are combined with an additional parameter (or threshold) called alpha.

The main objective of this project is to compare global and saturated probabilistic approximations using characteristics sets in mining incomplete data. Eight different data sets with 35% missing values were used for experiments. Two different variations of missing values were used, namely, lost values and "do not care" conditions. For rule induction, we implemented the single local probabilistic approximation version of MLEM2. We implemented a rule checker system to verify the accuracy of our generated ruleset by computing the error rate. Along with the rule checker system, the k-fold cross-validation technique was implemented with a value of k as ten to validate the generated rule sets. Finally, a statistical analysis was done for all the approaches using the Friedman test.

# References

1.	Patrick G. Clark, Jerzy W. Grzymala-Busse and Teresa Mroczek and Rafal Niemiec,
“A Comparison of Global and Saturated Probabilistic Approximations Using Characteristic Sets in Mining Incomplete Data”.
2.	J. W. Grzymala-Busse, P. G. Clark, and M. Kuehnhausen, “Generalized probabilistic approximations of incomplete data,” International Journal of Approximate Reasoning, vol. 132, 2014, pp. 180–196.
3.	Jerzy W. Grzymala-Busse, RULE INDUCTION, Chapter 1.
4.	Patrick G. Clark and Jerzy W. Grzymala-Busse, “A Comparison of Global and Local Probabilistic Approximations in Mining Data with Many Missing Attribute Values”.
5.	Friedman Test tool - https://www.medcalc.org/manual/friedman_test.php

