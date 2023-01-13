package com.friska.mrm.mcresources.blockstates;

import com.friska.mrm.system.annotations.ExpectAccess;

/**
 * There are two types of block state JSON files, <b>variants</b> and <b>multipart</b>. Each custom block state object requires a specific a BlockStateType of one of the below enums.
 * It is however, fine to leave the parameter null when using templates, as the templates themselves automatically define the block state type.
 * **/
@ExpectAccess
public enum BlockStateType {
    VARIANTS,
    MULTIPART
}
