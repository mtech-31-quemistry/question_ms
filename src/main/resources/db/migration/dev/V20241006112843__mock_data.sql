-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
    (6, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'PUBLISHED', '<p>Which of the following statements about reaction rates is correct?</p>',
     '[{"no":1,"text":"<p>The rate of reaction increases with increasing concentration of reactants.</p>",
     "explanation":"This statement is correct. Higher concentrations lead to more collisions between reactant particles, increasing the reaction rate.","isAnswer":true},
     {"no":2,"text":"<p>The rate of reaction is always constant during the course of the reaction.</p>",
     "explanation":"This statement is incorrect. The rate of reaction typically decreases as reactants are consumed.","isAnswer":false},
     {"no":3,"text":"<p>A catalyst decreases the rate of reaction.</p>","explanation":"This statement is incorrect. A catalyst increases the rate of reaction by lowering the activation energy.","isAnswer":false},
     {"no":4,"text":"<p>Temperature has no effect on reaction rates.</p>","explanation":"This statement is incorrect. Higher temperatures generally increase reaction rates by providing more energy to reactant molecules.","isAnswer":false}]',
     now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
    (6, 13);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
    (6, 7);

-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
    (7, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'PUBLISHED', '<p>Which of the following statements about equilibrium is correct?</p>',
     '[{"no":1,"text":"<p>At equilibrium, the concentrations of reactants and products are always equal.</p>",
     "explanation":"This statement is incorrect. At equilibrium, the concentrations of reactants and products remain constant but are not necessarily equal.","isAnswer":false},
     {"no":2,"text":"<p>Increasing the temperature favors the endothermic reaction in an equilibrium system.</p>",
     "explanation":"This statement is correct. According to Le Chatelier''s Principle, increasing temperature shifts the equilibrium in the direction of the endothermic reaction.","isAnswer":true},
{"no":3,"text":"<p>A catalyst shifts the position of the equilibrium to the right.</p>","explanation":"This statement is incorrect. A catalyst speeds up both forward and reverse reactions equally, without shifting the equilibrium position.","isAnswer":false},
{"no":4,"text":"<p>The equilibrium constant is always greater than 1 for exothermic reactions.</p>","explanation":"This statement is incorrect. The value of the equilibrium constant depends on the specific reaction conditions, not just whether it is exothermic or endothermic.","isAnswer":false}]',
 now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
(7, 11);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
(7, 6);

-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
(8, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'PUBLISHED', '<p>Which of the following statements about transition metals is correct?</p>',
'[{"no":1,"text":"<p>All transition metals are paramagnetic.</p>",
"explanation":"This statement is incorrect. Some transition metals are diamagnetic depending on their electronic configuration.","isAnswer":false},
{"no":2,"text":"<p>Transition metals form colored compounds due to d-d transitions.</p>",
"explanation":"This statement is correct. The partially filled d-orbitals in transition metals can cause the absorption of light, resulting in colored compounds.","isAnswer":true},
{"no":3,"text":"<p>All transition metals have a fixed oxidation state.</p>","explanation":"This statement is incorrect. Transition metals often exhibit variable oxidation states.","isAnswer":false},
{"no":4,"text":"<p>Transition metals form weak bonds with ligands.</p>","explanation":"This statement is incorrect. Transition metals often form strong coordinate bonds with ligands.","isAnswer":false}]',
 now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
(8, 17);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
(8, 9);

-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
(9, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'PUBLISHED', '<p>Which of the following statements about mass spectrometry is correct?</p>',
'[{"no":1,"text":"<p>Mass spectrometry measures the total energy of ions in a sample.</p>",
"explanation":"This statement is incorrect. Mass spectrometry measures the mass-to-charge ratio of ions.","isAnswer":false},
{"no":2,"text":"<p>In mass spectrometry, ions are separated based on their mass-to-charge ratio.</p>",
"explanation":"This statement is correct. The ions are separated by their mass-to-charge ratio, which provides information about the structure of the sample.","isAnswer":true},
{"no":3,"text":"<p>The sample is ionized by adding neutrons to the atoms.</p>","explanation":"This statement is incorrect. The sample is ionized by adding or removing electrons, not neutrons.","isAnswer":false},
{"no":4,"text":"<p>Mass spectrometry is used to measure the density of gases.</p>","explanation":"This statement is incorrect. Mass spectrometry is used to analyze the masses of ions, not the density of gases.","isAnswer":false}]',
 now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
(9, 20);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
(9, 10);

-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
(10, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'ARCHIVED', '<p>Which of the following statements about intermolecular forces is correct?</p>',
'[{"no":1,"text":"<p>Hydrogen bonds are weaker than van der Waals forces.</p>",
"explanation":"This statement is incorrect. Hydrogen bonds are stronger than van der Waals forces.","isAnswer":false},
{"no":2,"text":"<p>Van der Waals forces increase with the size of the molecule.</p>",
"explanation":"This statement is correct. Larger molecules have stronger van der Waals forces due to increased polarizability.","isAnswer":true},
{"no":3,"text":"<p>Dipole-dipole interactions occur between non-polar molecules.</p>","explanation":"This statement is incorrect. Dipole-dipole interactions occur between polar molecules.","isAnswer":false},
{"no":4,"text":"<p>All intermolecular forces have the same strength.</p>","explanation":"This statement is incorrect. Different intermolecular forces have different strengths, with hydrogen bonds generally being the strongest.","isAnswer":false}]',
     now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
    (10, 5);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
    (10, 3);

-- Adjust sequence numbers
ALTER SEQUENCE qms_question.mcq_id_seq
    RESTART 11;
