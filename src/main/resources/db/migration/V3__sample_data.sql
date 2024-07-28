-- Topics
INSERT INTO qms_question.topic (id, "name")
VALUES
    (1, 'Atomic Structure'),
    (2, 'Chemical Bonding'),
    (3, 'States of Matter'),
    (4, 'Chemical Energetics'),
    (5, 'Redox Reactions'),
    (6, 'Equilibria'),
    (7, 'Kinetics'),
    (8, 'Organic Chemistry'),
    (9, 'Inorganic Chemistry'),
    (10, 'Analytical Techniques');

-- Skills
INSERT INTO qms_question.skill (id, "name", topic_id)
VALUES
    (1, 'Electron Configuration', 1),
    (2, 'Ionisation Energy', 1),
    (3, 'Ionic Bonding', 2),
    (4, 'Covalent Bonding', 2),
    (5, 'Intermolecular Forces', 3),
    (6, 'Ideal Gas Law', 3),
    (7, 'Enthalpy Changes', 4),
    (8, 'Hess''s Law', 4),
    (9, 'Oxidation Numbers', 5),
    (10, 'Balancing Redox Equations', 5),
    (11, 'Le Chatelier''s Principle', 6),
    (12, 'Equilibrium Constants', 6),
    (13, 'Rate of Reaction', 7),
    (14, 'Activation Energy', 7),
    (15, 'Functional Groups', 8),
    (16, 'Reaction Mechanisms', 8),
    (17, 'Transition Metals', 9),
    (18, 'Group 2 Elements', 9),
    (19, 'Infrared Spectroscopy', 10),
    (20, 'Mass Spectrometry', 10);


-- MCQ Question
INSERT INTO qms_question.mcq
(id, published_on, closed_by, created_by, published_by, status, stem, "options", closed_on, created_on)
VALUES
(1, '2024-07-23 08:00:00.000', NULL, 'Mr. Lee', 'Mr. Lee', 'Draft', '<p>Which of the following statements about covalent bonding is correct?</p>',
'[{"no":1,"text":"<p>Covalent bonds are formed by the transfer of electrons from one atom to another.</p>",
"explanation":"This statement is incorrect. Covalent bonds involve the sharing of electron pairs between atoms, not the transfer of electrons.","isAnswer":false},
{"no":2,"text":"<p>Covalent bonds are formed between metals and nonmetals.</p>",
"explanation":"This statement is incorrect. Covalent bonds are typically formed between nonmetals.","isAnswer":false},
{"no":3,"text":"<p>In a covalent bond, electrons are shared between atoms.</p>",
"explanation":"This statement is correct. Covalent bonds involve the sharing of electron pairs between atoms.","isAnswer":true},
{"no":4,"text":"<p>Covalent bonds can only occur in molecules with symmetrical shapes.</p>",
"explanation":"This statement is incorrect. Covalent bonds can occur in molecules with various shapes, not just symmetrical ones.","isAnswer":false}]',
NULL, '2024-07-23 08:00:00.000');

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
(id, published_on, closed_by, created_by, published_by, status, stem, "options", closed_on, created_on)
VALUES
(2, '2024-07-23 08:00:00.000', NULL, 'Mr. Lee', 'Mr. Lee', 'Draft', '<p>Which of the following statements about electron configuration is correct?</p>',
'[{"no":1,"text":"<p>The 3d subshell is filled before the 4s subshell.</p>",
"explanation":"This statement is incorrect. The 4s subshell is filled before the 3d subshell.","isAnswer":false},
{"no":2,"text":"<p>Electrons in the same orbital must have opposite spins.</p>",
"explanation":"This statement is correct. Electrons in the same orbital must have opposite spins according to the Pauli Exclusion Principle.","isAnswer":true},
{"no":3,"text":"<p>An orbital can hold a maximum of three electrons.</p>","explanation":"This statement is incorrect. An orbital can hold a maximum of two electrons.","isAnswer":false},
{"no":4,"text":"<p>The 2p subshell can hold a maximum of four electrons.</p>","explanation":"This statement is incorrect. The 2p subshell can hold a maximum of six electrons.","isAnswer":false}]',
NULL, '2024-07-23 08:00:00.000');

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
(id, published_on, closed_by, created_by, published_by, status, stem, "options", closed_on, created_on)
VALUES
(3, '2024-07-23 08:00:00.000', NULL, 'Mr. Lee', 'Mr. Lee', 'Draft', '<p>Which of the following statements about enthalpy changes is correct?</p>',
'[{"no":1,"text":"<p>The enthalpy change of a reaction is independent of the route taken.</p>",
"explanation":"This statement is correct. According to Hess''s Law, the enthalpy change of a reaction is independent of the route taken.","isAnswer":true},
{"no":2,"text":"<p>Exothermic reactions absorb heat from the surroundings.</p>",
"explanation":"This statement is incorrect. Exothermic reactions release heat to the surroundings.","isAnswer":false},
{"no":3,"text":"<p>The enthalpy change of formation is always negative.</p>","explanation":"This statement is incorrect. The enthalpy change of formation can be positive or negative.","isAnswer":false},
{"no":4,"text":"<p>The enthalpy change of neutralization is the enthalpy change when one mole of water is formed from the reaction between an acid and a base.</p>","explanation":"This statement is correct.","isAnswer":true}]',
NULL, '2024-07-23 08:00:00.000');

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
