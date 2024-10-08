-- Topics
INSERT INTO qms_question.topic (id, "name", status)
VALUES
    (1, 'Atomic Structure', 'ACTIVE'),
    (2, 'Chemical Bonding','ACTIVE'),
    (3, 'States of Matter','ACTIVE'),
    (4, 'Chemical Energetics','ACTIVE'),
    (5, 'Redox Reactions','ACTIVE'),
    (6, 'Equilibria','ACTIVE'),
    (7, 'Kinetics','ACTIVE'),
    (8, 'Organic Chemistry','ACTIVE'),
    (9, 'Inorganic Chemistry','ACTIVE'),
    (10, 'Analytical Techniques','ACTIVE');

-- Skills
INSERT INTO qms_question.skill (id, "name", status, topic_id)
VALUES
    (1, 'Electron Configuration','ACTIVE', 1),
    (2, 'Ionisation Energy','ACTIVE', 1),
    (3, 'Ionic Bonding','ACTIVE', 2),
    (4, 'Covalent Bonding','ACTIVE', 2),
    (5, 'Intermolecular Forces','ACTIVE', 3),
    (6, 'Ideal Gas Law','ACTIVE', 3),
    (7, 'Enthalpy Changes','ACTIVE', 4),
    (8, 'Hess''s Law','ACTIVE', 4),
    (9, 'Oxidation Numbers','ACTIVE', 5),
    (10, 'Balancing Redox Equations','ACTIVE', 5),
    (11, 'Le Chatelier''s Principle','ACTIVE', 6),
    (12, 'Equilibrium Constants','ACTIVE', 6),
    (13, 'Rate of Reaction','ACTIVE', 7),
    (14, 'Activation Energy','ACTIVE', 7),
    (15, 'Functional Groups','ACTIVE', 8),
    (16, 'Reaction Mechanisms','ACTIVE', 8),
    (17, 'Transition Metals','ACTIVE', 9),
    (18, 'Group 2 Elements','ACTIVE', 9),
    (19, 'Infrared Spectroscopy','ACTIVE', 10),
    (20, 'Mass Spectrometry','ACTIVE', 10);


-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on,  created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
(1, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'DRAFT', '<p>Which of the following statements about covalent bonding is correct?</p>',
'[{"no":1,"text":"<p>Covalent bonds are formed by the transfer of electrons from one atom to another.</p>",
"explanation":"This statement is incorrect. Covalent bonds involve the sharing of electron pairs between atoms, not the transfer of electrons.","isAnswer":false},
{"no":2,"text":"<p>Covalent bonds are formed between metals and nonmetals.</p>",
"explanation":"This statement is incorrect. Covalent bonds are typically formed between nonmetals.","isAnswer":false},
{"no":3,"text":"<p>In a covalent bond, electrons are shared between atoms.</p>",
"explanation":"This statement is correct. Covalent bonds involve the sharing of electron pairs between atoms.","isAnswer":true},
{"no":4,"text":"<p>Covalent bonds can only occur in molecules with symmetrical shapes.</p>",
"explanation":"This statement is incorrect. Covalent bonds can occur in molecules with various shapes, not just symmetrical ones.","isAnswer":false}]',
 now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
(1, 4);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
(1, 2);


-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options",  created_on, updated_on)
VALUES
(2, '2024-07-23 08:00:00.000',  'Mr. Lee', 'Mr. Lee', 'DRAFT', '<p>Which of the following statements about electron configuration is correct?</p>',
'[{"no":1,"text":"<p>The 3d subshell is filled before the 4s subshell.</p>",
"explanation":"This statement is incorrect. The 4s subshell is filled before the 3d subshell.","isAnswer":false},
{"no":2,"text":"<p>Electrons in the same orbital must have opposite spins.</p>",
"explanation":"This statement is correct. Electrons in the same orbital must have opposite spins according to the Pauli Exclusion Principle.","isAnswer":true},
{"no":3,"text":"<p>An orbital can hold a maximum of three electrons.</p>","explanation":"This statement is incorrect. An orbital can hold a maximum of two electrons.","isAnswer":false},
{"no":4,"text":"<p>The 2p subshell can hold a maximum of four electrons.</p>","explanation":"This statement is incorrect. The 2p subshell can hold a maximum of six electrons.","isAnswer":false}]',
 now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
(2, 1);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
(2, 1);



-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
(3, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'DRAFT', '<p>Which of the following statements about enthalpy changes is correct?</p>',
'[{"no":1,"text":"<p>The enthalpy change of a reaction is independent of the route taken.</p>",
"explanation":"This statement is correct. According to Hess''s Law, the enthalpy change of a reaction is independent of the route taken.","isAnswer":true},
{"no":2,"text":"<p>Exothermic reactions absorb heat from the surroundings.</p>",
"explanation":"This statement is incorrect. Exothermic reactions release heat to the surroundings.","isAnswer":false},
{"no":3,"text":"<p>The enthalpy change of formation is always negative.</p>","explanation":"This statement is incorrect. The enthalpy change of formation can be positive or negative.","isAnswer":false},
{"no":4,"text":"<p>The enthalpy change of neutralization is the enthalpy change when one mole of water is formed from the reaction between an acid and a base.</p>","explanation":"This statement is correct.","isAnswer":true}]',
 now(),now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
(3, 7);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
(3, 4);



-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
(4, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'PUBLISHED', '<p>Which of the following statements about oxidation numbers is correct?</p>',
'[{"no":1,"text":"<p>The oxidation number of oxygen in peroxides is -2.</p>",
"explanation":"This statement is incorrect. The oxidation number of oxygen in peroxides is -1.","isAnswer":false},
{"no":2,"text":"<p>The oxidation number of hydrogen in hydrides is +1.</p>",
"explanation":"This statement is incorrect. The oxidation number of hydrogen in hydrides is -1.","isAnswer":false},
{"no":3,"text":"<p>The sum of the oxidation numbers in a neutral compound is zero.</p>",
"explanation":"This statement is correct. The sum of the oxidation numbers in a neutral compound is indeed zero.","isAnswer":true},
{"no":4,"text":"<p>The oxidation number of fluorine is always +1 in its compounds.</p>",
"explanation":"This statement is incorrect. The oxidation number of fluorine is always -1 in its compounds.","isAnswer":false}]',
 now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
(4, 9);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
(4, 5);


-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, created_by, published_by, status, stem, "options", created_on, updated_on)
VALUES
(5, '2024-07-23 08:00:00.000', 'Mr. Lee', 'Mr. Lee', 'PUBLISHED', '<p>Which of the following statements about the Ideal Gas Law is correct?</p>',
'[{"no":1,"text":"<p>The Ideal Gas Law is only applicable at high pressures and low temperatures.</p>",
"explanation":"This statement is incorrect. The Ideal Gas Law is most accurate at low pressures and high temperatures.","isAnswer":false},
{"no":2,"text":"<p>The volume of an ideal gas is directly proportional to the number of moles at constant pressure and temperature.</p>",
"explanation":"This statement is correct. According to the Ideal Gas Law (PV = nRT), the volume is directly proportional to the number of moles when pressure and temperature are constant.","isAnswer":true},
{"no":3,"text":"<p>The Ideal Gas Law can be derived from Avogadro''s Law and Boyle''s Law alone.</p>",
"explanation":"This statement is incorrect. The Ideal Gas Law is derived from Avogadro''s Law, Boyle''s Law, and Charles''s Law.","isAnswer":false},
{"no":4,"text":"<p>The value of the gas constant R varies with the gas being considered.</p>",
"explanation":"This statement is incorrect. The value of the gas constant R is universal and does not vary with the gas.","isAnswer":false}]',
 now(), now());

-- Associated Skills
INSERT INTO qms_question.mcq_skills
(mcq_id, skill_id)
VALUES
(5, 6);

-- Associated Topics
INSERT INTO qms_question.mcq_topics
(mcq_id, topic_id)
VALUES
(5, 3);


ALTER SEQUENCE qms_question.topic_id_seq
	RESTART 11;

ALTER SEQUENCE qms_question.skill_id_seq
	RESTART 21;

ALTER SEQUENCE qms_question.mcq_id_seq
    RESTART 6;